package org.esgi.boissipay.billing.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.billing.BillingRepository;
import org.esgi.boissipay.billing.infra.BillingFactory;
import org.esgi.boissipay.billing.models.Billing;
import org.esgi.boissipay.kafka.KafkaException;
import org.esgi.boissipay.kafka.schema.NewContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final BillingRepository billingRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Consumer(BillingRepository billingRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.billingRepository = Objects.requireNonNull(billingRepository);
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "#{'${kafka.topic.create-contract}'.split(',')}")
    public void consume(String message) {
        var mapper = new ObjectMapper();
        logger.info("Received message: " + message);

        NewContract newContract = null;
        try {
            newContract = mapper.readValue(message, NewContract.class);
        } catch(IOException ex) {
            throw new KafkaException(ex);
        }
        Billing billing = BillingFactory.createFrom(newContract);
        billingRepository.save(billing);

        String billingAsString = null;
        try {
        billingAsString = mapper.writeValueAsString(billing);

        } catch(IOException ex) {
           throw new KafkaException(ex);
        }

        kafkaTemplate.send("respond-topic", billingAsString);
    }
}
