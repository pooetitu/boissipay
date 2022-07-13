package org.esgi.boissipay.billing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.esgi.boissipay.billing.domain.PaymentRepository;
import org.esgi.boissipay.billing.infra.EventDispatcher;
import org.esgi.boissipay.billing.infra.InMemoryPaymentRepository;
import org.esgi.boissipay.billing.kafka.Producer;
import org.esgi.boissipay.billing.use_case.CreatePaymentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashSet;

@Configuration
public class BillingConfiguration {

    private final Producer producer;

    @Autowired
    public BillingConfiguration(Producer producer) {
        this.producer = producer;
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return InMemoryPaymentRepository.newEmptyInMemoryBillingRepository();
    }

    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Bean
    public EventDispatcher eventDispatcher() {
        return new EventDispatcher(new HashSet<>(Collections.singleton(producer)));
    }

    @Bean
    public CreatePaymentUseCase createPaymentUseCase() {
        return new CreatePaymentUseCase(paymentRepository(), objectMapper(), eventDispatcher());
    }
}
