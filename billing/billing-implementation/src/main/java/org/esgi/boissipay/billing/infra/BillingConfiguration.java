package org.esgi.boissipay.billing.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.esgi.boissipay.billing.domain.event.CreateContractHandler;
import org.esgi.boissipay.billing.domain.event.EventDispatcher;
import org.esgi.boissipay.billing.domain.event.InvoiceSentHandler;
import org.esgi.boissipay.billing.domain.event.NewOperationHandler;
import org.esgi.boissipay.billing.domain.event.PaymentSuccessHandler;
import org.esgi.boissipay.billing.domain.event.ProcessPaymentHandler;
import org.esgi.boissipay.billing.domain.event.SendInvoiceHandler;
import org.esgi.boissipay.billing.domain.repository.ContractRepository;
import org.esgi.boissipay.billing.domain.repository.OperationRepository;
import org.esgi.boissipay.billing.domain.repository.OrderRepository;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;
import org.esgi.boissipay.billing.infra.repository.JPAContractRepository;
import org.esgi.boissipay.billing.infra.repository.JPAOperationRepository;
import org.esgi.boissipay.billing.infra.repository.JPAOrderRepository;
import org.esgi.boissipay.billing.infra.repository.JPAPaymentRepository;
import org.esgi.boissipay.billing.infra.repository.SpringDataContractRepository;
import org.esgi.boissipay.billing.infra.repository.SpringDataOperationRepository;
import org.esgi.boissipay.billing.infra.repository.SpringDataOrderRepository;
import org.esgi.boissipay.billing.infra.repository.SpringDataPaymentRepository;
import org.esgi.boissipay.billing.kafka.ProcessPaymentProducer;
import org.esgi.boissipay.billing.kafka.SendInvoiceProducer;
import org.esgi.boissipay.billing.use_case.BillPaymentUseCase;
import org.esgi.boissipay.billing.use_case.GetInvoiceByInvoiceRef;
import org.esgi.boissipay.billing.use_case.GetOperationUseCase;
import org.esgi.boissipay.billing.use_case.GetOperationsByInvoiceRefUseCase;
import org.esgi.boissipay.billing.use_case.NewContractUseCase;
import org.esgi.boissipay.billing.use_case.NewOperationUseCase;
import org.esgi.boissipay.billing.use_case.NewOrderUseCase;
import org.esgi.boissipay.billing.use_case.NewPaymentUseCase;
import org.esgi.boissipay.billing.use_case.GetInvoiceUseCase;
import org.esgi.boissipay.billing.use_case.SetPaymentInvoiceRefUseCase;
import org.esgi.boissipay.billing.use_case.ValidatePaymentUseCase;
import org.esgi.boissipay.billing.use_case.handler.CreateContractEventHandler;
import org.esgi.boissipay.billing.use_case.handler.InvoiceSentEventHandler;
import org.esgi.boissipay.billing.use_case.handler.NewOperationEventHandler;
import org.esgi.boissipay.billing.use_case.handler.PaymentSuccessEventHandler;
import org.esgi.boissipay.billing.use_case.handler.SendInvoiceEventHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class BillingConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Bean
    ContractRepository contractRepository(JPAContractRepository jpaContractRepository) {
        return new SpringDataContractRepository(jpaContractRepository);
    }

    @Bean
    OperationRepository operationRepository(JPAOperationRepository jpaOperationRepository, JPAOrderRepository jpaOrderRepository) {
        return new SpringDataOperationRepository(jpaOperationRepository, jpaOrderRepository);
    }

    @Bean
    OrderRepository orderRepository(JPAOrderRepository jpaOrderRepository) {
        return new SpringDataOrderRepository(jpaOrderRepository);
    }

    @Bean
    PaymentRepository paymentRepository(JPAPaymentRepository jpaPaymentRepository, OperationRepository operationRepository) {
        return new SpringDataPaymentRepository(jpaPaymentRepository, operationRepository);
    }

    @Bean
    ProcessPaymentProducer processPaymentProducer(
        @Value("${kafka.topic.process-payment}") String processPaymentTopicName,
        KafkaTemplate<String, String> kafkaTemplate,
        ObjectMapper objectMapper
    ) {
        return new ProcessPaymentProducer(processPaymentTopicName, kafkaTemplate, objectMapper);
    }

    @Bean
    SendInvoiceProducer sendInvoiceProducer(
        @Value("${kafka.topic.send-invoice}") String sendInvoiceTopicName,
        KafkaTemplate<String, String> kafkaTemplate,
        ObjectMapper objectMapper
    ) {
        return new SendInvoiceProducer(sendInvoiceTopicName, kafkaTemplate, objectMapper);
    }

    @Bean
    BillPaymentUseCase billPaymentUseCase(PaymentRepository paymentRepository) {
        return new BillPaymentUseCase(paymentRepository);
    }

    @Bean
    NewContractUseCase newContractUseCase(ContractRepository contractRepository) {
        return new NewContractUseCase(contractRepository);
    }

    @Bean
    NewOperationUseCase newOperationUseCase(NewOrderUseCase newOrderUseCase, OperationRepository operationRepository, EventDispatcher eventDispatcher) {
        return new NewOperationUseCase(newOrderUseCase, operationRepository, eventDispatcher);
    }

    @Bean
    NewOrderUseCase newOrderUseCase(OrderRepository orderRepository) {
        return new NewOrderUseCase(orderRepository);
    }

    @Bean
    NewPaymentUseCase newPaymentUseCase(PaymentRepository paymentRepository, EventDispatcher eventDispatcher) {
        return new NewPaymentUseCase(paymentRepository, eventDispatcher);
    }

    @Bean
    GetInvoiceByInvoiceRef getInvoiceByInvoiceRef(PaymentRepository paymentRepository, ContractRepository contractRepository) {
        return new GetInvoiceByInvoiceRef(paymentRepository, contractRepository);
    }

    @Bean
    GetInvoiceUseCase searchInvoiceUseCase(PaymentRepository paymentRepository, ContractRepository contractRepository) {
        return new GetInvoiceUseCase(paymentRepository, contractRepository);
    }

    @Bean
    CreateContractEventHandler createContractEventHandler(NewContractUseCase newContractUseCase) {
        return new CreateContractEventHandler(newContractUseCase);
    }

    @Bean
    ValidatePaymentUseCase validatePaymentUseCase(PaymentRepository paymentRepository) {
        return new ValidatePaymentUseCase(paymentRepository);
    }

    @Bean
    GetOperationUseCase getOperationUseCase(OperationRepository operationRepository) {
        return new GetOperationUseCase(operationRepository);
    }

    @Bean
    GetOperationsByInvoiceRefUseCase getOperationsByInvoiceRefUseCase(PaymentRepository paymentRepository) {
        return new GetOperationsByInvoiceRefUseCase(paymentRepository);
    }

    @Bean
    InvoiceSentEventHandler invoiceSentEventHandler(BillPaymentUseCase billPaymentUseCase) {
        return new InvoiceSentEventHandler(billPaymentUseCase);
    }

    @Bean
    NewOperationEventHandler newOperationEventHandler(NewPaymentUseCase newPaymentUseCase) {
        return new NewOperationEventHandler(newPaymentUseCase);
    }

    @Bean
    PaymentSuccessEventHandler paymentSuccessEventHandler(ValidatePaymentUseCase validatePaymentUseCase) {
        return new PaymentSuccessEventHandler(validatePaymentUseCase);
    }

    @Bean
    SendInvoiceEventHandler sendInvoiceEventHandler(SetPaymentInvoiceRefUseCase setPaymentInvoiceRefUseCase) {
        return new SendInvoiceEventHandler(setPaymentInvoiceRefUseCase);
    }

    @Bean
    SetPaymentInvoiceRefUseCase setPaymentInvoiceRefUseCase(PaymentRepository paymentRepository) {
        return new SetPaymentInvoiceRefUseCase(paymentRepository);
    }


    @Bean
    public Set<CreateContractHandler> createContractHandlers(CreateContractEventHandler createContractEventHandler) {
        Set<CreateContractHandler> set = new HashSet<>();
        set.add(createContractEventHandler);
        return set;
    }

    @Bean
    public Set<InvoiceSentHandler> invoiceSentHandlers(InvoiceSentEventHandler invoiceSentEventHandler) {
        Set<InvoiceSentHandler> set = new HashSet<>();
        set.add(invoiceSentEventHandler);
        return set;
    }

    @Bean
    public Set<PaymentSuccessHandler> paymentSuccessHandlers(PaymentSuccessEventHandler paymentSuccessEventHandler) {
        Set<PaymentSuccessHandler> set = new HashSet<>();
        set.add(paymentSuccessEventHandler);
        return set;
    }

    @Bean
    public Set<ProcessPaymentHandler> processPaymentHandlers(ProcessPaymentProducer processPaymentProducer) {
        Set<ProcessPaymentHandler> set = new HashSet<>();
        set.add(processPaymentProducer);
        return set;
    }

    @Bean
    public Set<SendInvoiceHandler> sendInvoiceHandlers(SendInvoiceProducer sendInvoiceProducer, SendInvoiceEventHandler sendInvoiceEventHandler) {
        Set<SendInvoiceHandler> set = new HashSet<>();
        set.add(sendInvoiceProducer);
        set.add(sendInvoiceEventHandler);
        return set;
    }

    @Bean
    public Set<NewOperationHandler> newOperationHandlers(NewOperationEventHandler newOperationEventHandler) {
        Set<NewOperationHandler> set = new HashSet<>();
        set.add(newOperationEventHandler);
        return set;
    }

    @Bean
    public EventDispatcher eventDispatcher() {
        return new DefaultEventDispatcher();
    }
}
