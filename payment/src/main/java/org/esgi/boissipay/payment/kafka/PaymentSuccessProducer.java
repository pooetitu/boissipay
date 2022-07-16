package org.esgi.boissipay.payment.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.kafka.KafkaException;
import org.esgi.boissipay.payment.domain.Payment;
import org.esgi.boissipay.payment.kernel.PaymentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;

public class PaymentSuccessProducer {

    private static final Logger logger = LoggerFactory.getLogger(PaymentSuccessProducer.class);

    private final String paymentSuccessTopicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper mapper;

    public PaymentSuccessProducer(String paymentSuccessTopicName, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper mapper) {
        this.paymentSuccessTopicName = paymentSuccessTopicName;
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
    }

    public void onPaymentSuccess(Payment payment) {
        logger.info("Sending payment success event to Kafka");
        String message;
        try {
            message = mapper.writeValueAsString(PaymentMapper.toPaymentSuccess(payment));
        } catch (IOException ex) {
            throw new KafkaException(ex);
        }

        kafkaTemplate.send(paymentSuccessTopicName, message);
    }
}
