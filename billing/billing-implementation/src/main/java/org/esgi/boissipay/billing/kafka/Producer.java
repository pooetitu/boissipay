package org.esgi.boissipay.billing.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.billing.domain.CreatedBillingListener;
import org.esgi.boissipay.billing.exposition.CreateBillingRequest;
import org.esgi.boissipay.kafka.KafkaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public final class Producer implements CreatedBillingListener {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private final String createBillingTopicName;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public Producer(@Value("create-billing") String createBillingTopicName, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.createBillingTopicName = createBillingTopicName;
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = objectMapper;
    }

    public void onBillCreated(CreateBillingRequest request) {
        logger.info("sending billing created event to Kafka");
        var message = billingToString(request);
        kafkaTemplate.send(createBillingTopicName, message);
    }

    private String billingToString(CreateBillingRequest billing) {
        String billingAsString = null;
        try {
            billingAsString = mapper.writeValueAsString(billing);

        } catch(IOException ex) {
            throw new KafkaException(ex);
        }
        return billingAsString;
    }
}
