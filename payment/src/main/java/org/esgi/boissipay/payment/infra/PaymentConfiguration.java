package org.esgi.boissipay.payment.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.esgi.boissipay.payment.domain.PaymentHandler;
import org.esgi.boissipay.payment.kafka.PaymentSuccessProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class PaymentConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Bean
    PaymentHandler paymentHandler(PaymentSuccessProducer paymentSuccessProducer) {
        return new DefaultPaymentHandler(paymentSuccessProducer);
    }

    @Bean
    PaymentSuccessProducer paymentSuccessProducer(
        @Value("${kafka.topic.payment-success}") String paymentSuccessTopicName,
        KafkaTemplate<String, String> kafkaTemplate,
        ObjectMapper mapper
    ) {
        return new PaymentSuccessProducer(paymentSuccessTopicName, kafkaTemplate, mapper);
    }
}
