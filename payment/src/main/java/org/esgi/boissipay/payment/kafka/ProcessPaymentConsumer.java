package org.esgi.boissipay.payment.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.kafka.KafkaException;
import org.esgi.boissipay.kafka.schema.ProcessPayment;
import org.esgi.boissipay.payment.domain.PaymentHandler;
import org.esgi.boissipay.payment.kernel.PaymentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProcessPaymentConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ProcessPaymentConsumer.class);

    private final PaymentHandler paymentHandler;

    private final ObjectMapper mapper;

    public ProcessPaymentConsumer(PaymentHandler paymentHandler, ObjectMapper mapper) {
        this.paymentHandler = paymentHandler;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "${kafka.topic.process-payment}")
    public void consume(String message) {
        logger.info("Received process payment message: " + message);
        ProcessPayment processPayment;
        try {
            processPayment = mapper.readValue(message, ProcessPayment.class);
        } catch (JsonProcessingException e) {
            throw new KafkaException(e);
        }
        paymentHandler.onProcessPayment(PaymentMapper.toPayment(processPayment));
    }

}
