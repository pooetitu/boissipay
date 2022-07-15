package org.esgi.boissipay.billing.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.billing.domain.EventDispatcher;
import org.esgi.boissipay.billing.kernel.ContractMapper;
import org.esgi.boissipay.kafka.KafkaException;
import org.esgi.boissipay.kafka.schema.NewContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public final class CreateContractConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CreateContractConsumer.class);
    private final EventDispatcher eventDispatcher;
    private final ObjectMapper mapper;

    public CreateContractConsumer(EventDispatcher eventDispatcher, ObjectMapper mapper) {
        this.eventDispatcher = eventDispatcher;
        this.mapper = mapper;
    }


    @KafkaListener(topics = "${kafka.topic.create-contract}")
    public void consume(String message) {
        logger.info("Received create contract message: " + message);
        NewContract newContract;
        try {
            newContract = mapper.readValue(message, NewContract.class);
        } catch (JsonProcessingException e) {
            throw new KafkaException(e);
        }
        eventDispatcher.dispatchCreateContact(ContractMapper.toContract(newContract));
    }


}
