package org.esgi.boissipay.billing.kafka;

import org.esgi.boissipay.billing.use_case.CreatePaymentUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public final class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final CreatePaymentUseCase createPaymentUseCase;

    public Consumer(CreatePaymentUseCase createPaymentUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
    }

    @KafkaListener(topics = "#{'${kafka.topic.create-contract}'.split(',')}")
    public void consume(String message) {
        logger.info("Received message: " + message);
        createPaymentUseCase.createBilling(message);
    }


}
