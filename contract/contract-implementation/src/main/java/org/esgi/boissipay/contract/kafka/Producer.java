package org.esgi.boissipay.contract.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.contract.model.ContractRequest;
import org.esgi.boissipay.kafka.KafkaException;
import org.esgi.boissipay.kafka.schema.NewContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private final String createContractTopicName;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public Producer(@Value("${kafka.topic.create-contract}") String createContractTopicName, KafkaTemplate<String, String> kafkaTemplate) {
        this.createContractTopicName = createContractTopicName;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendContractCreateEvent(ContractRequest request) {
        logger.info("Sending contract creation event to Kafka");
        var newContract = new NewContract()
                .setName(request.getProductRef().getValue());
        String stringNewContract = null;
        try {
            stringNewContract = objectMapper.writeValueAsString(newContract);
        } catch (JsonProcessingException e) {
            throw new KafkaException(e);
        }
        kafkaTemplate.send(createContractTopicName, stringNewContract);
    }
}
