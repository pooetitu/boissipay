package org.esgi.boissipay.billing.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;
import org.esgi.boissipay.billing.kafka.ProcessPaymentProducer;
import org.esgi.boissipay.billing.use_case.CreatePaymentUseCase;
import org.esgi.boissipay.billing.use_case.NotifyInvoiceUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Collections;
import java.util.HashSet;

@Configuration
public class BillingConfiguration {

    @Bean
    public ProcessPaymentProducer producer(@Value("${kafka.topic.create-billing}") String createBillingTopicName, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        return new ProcessPaymentProducer(createBillingTopicName, kafkaTemplate, objectMapper);
    }

    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Bean
    public DefaultEventDispatcher eventDispatcher(ProcessPaymentProducer processPaymentProducer) {
        return new DefaultEventDispatcher(new HashSet<>(Collections.singleton(processPaymentProducer)), new HashSet<>(Collections.singleton(notifyInvoiceUseCase())));
    }

    @Bean
    public CreatePaymentUseCase createPaymentUseCase(ProcessPaymentProducer processPaymentProducer, PaymentRepository paymentRepository) {
        return new CreatePaymentUseCase(paymentRepository, objectMapper(), eventDispatcher(processPaymentProducer));
    }

    @Bean
    public NotifyInvoiceUseCase notifyInvoiceUseCase() {
        return new NotifyInvoiceUseCase();
    }
}
