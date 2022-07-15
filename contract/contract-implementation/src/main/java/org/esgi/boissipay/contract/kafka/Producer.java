package org.esgi.boissipay.contract.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.contract.kernel.ContractMapper;
import org.esgi.boissipay.contract.domain.Contract;
import org.esgi.boissipay.contract.domain.CreateContractHandler;
import org.esgi.boissipay.kafka.KafkaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer implements CreateContractHandler {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private final String createContractTopicName;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public Producer(@Value("${kafka.topic.create-contract}") String createContractTopicName, KafkaTemplate<String, String> kafkaTemplate) {
        this.createContractTopicName = createContractTopicName;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    private void sendContractCreateEvent(Contract request) {
        logger.info("Sending contract creation event to Kafka");

        String stringNewContract;
        try {
            stringNewContract = objectMapper.writeValueAsString(ContractMapper.toNewContract(request));
        } catch (JsonProcessingException e) {
            throw new KafkaException(e);
        }
        kafkaTemplate.send(createContractTopicName, stringNewContract);
    }

    @Override
    public void onContractCreated(Contract contract) {
        sendContractCreateEvent(contract);
    }
}
