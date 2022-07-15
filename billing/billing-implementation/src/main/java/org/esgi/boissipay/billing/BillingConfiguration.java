package org.esgi.boissipay.billing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.esgi.boissipay.billing.domain.PaymentRepository;
import org.esgi.boissipay.billing.infra.EventDispatcher;
import org.esgi.boissipay.billing.infra.InMemoryPaymentRepository;
import org.esgi.boissipay.billing.kafka.Producer;
import org.esgi.boissipay.billing.use_case.CreatePaymentUseCase;
import org.esgi.boissipay.billing.use_case.NotifyInvoiceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashSet;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class BillingConfiguration {

    @Bean
    public PaymentRepository paymentRepository() {
        return InMemoryPaymentRepository.newEmptyInMemoryBillingRepository();
    }

    @Bean
    public Producer producer(@Value("${kafka.topic.create-billing}") String createBillingTopicName, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        return new Producer(createBillingTopicName, kafkaTemplate, objectMapper);
    }

    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Bean
    public EventDispatcher eventDispatcher(Producer producer) {
        return new EventDispatcher(new HashSet<>(Collections.singleton(producer)), new HashSet<>(Collections.singleton(notifyInvoiceUseCase())));
    }

    @Bean
    public CreatePaymentUseCase createPaymentUseCase(Producer producer) {
        return new CreatePaymentUseCase(paymentRepository(), objectMapper(), eventDispatcher(producer));
    }

    @Bean
    public NotifyInvoiceUseCase notifyInvoiceUseCase() {
        return new NotifyInvoiceUseCase();
    }
}
