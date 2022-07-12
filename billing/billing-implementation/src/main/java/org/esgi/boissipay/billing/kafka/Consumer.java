package org.esgi.boissipay.billing.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "#{'${kafka.topic.create-contract}'.split(',')}")
    public void consume(String message) {
        logger.info("Received message: " + message);
    }
}
