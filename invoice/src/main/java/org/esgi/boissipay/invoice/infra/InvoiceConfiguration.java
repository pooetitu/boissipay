package org.esgi.boissipay.invoice.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.esgi.boissipay.invoice.domain.EmailHandler;
import org.esgi.boissipay.invoice.kafka.InvoiceSentProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class InvoiceConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Bean
    EmailHandler emailHandler(InvoiceSentProducer invoiceSentProducer) {
        return new DefaultEmailHandler(invoiceSentProducer);
    }

    @Bean
    InvoiceSentProducer invoiceSentProducer(
        @Value("${kafka.topic.invoice-sent}") String invoiceSentTopicName,
        KafkaTemplate<String, String> kafkaTemplate,
        ObjectMapper mapper
    ) {
        return new InvoiceSentProducer(invoiceSentTopicName, kafkaTemplate, mapper);
    }
}
