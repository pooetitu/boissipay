package org.esgi.boissipay.billing.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.billing.domain.ProcessPaymentHandler;
import org.esgi.boissipay.billing.domain.models.Payment;
import org.esgi.boissipay.billing.kernel.PaymentMapper;
import org.esgi.boissipay.kafka.KafkaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;

public final class ProcessPaymentProducer implements ProcessPaymentHandler {
    private static final Logger logger = LoggerFactory.getLogger(ProcessPaymentProducer.class);
    private final String processPaymentTopicName;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public ProcessPaymentProducer(String processPaymentTopicName, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.processPaymentTopicName = processPaymentTopicName;
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = objectMapper;
    }

    @Override
    public void onProcessPayment(Payment payment) {
        logger.info("Sending process payment event to Kafka");
        String message;
        try {
            message = mapper.writeValueAsString(PaymentMapper.toProcessPayment(payment));
        } catch (IOException ex) {
            throw new KafkaException(ex);
        }

        kafkaTemplate.send(processPaymentTopicName, message);
    }
}
