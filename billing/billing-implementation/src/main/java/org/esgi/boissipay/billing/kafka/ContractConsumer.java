package org.esgi.boissipay.billing.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.billing.use_case.CreatePaymentUseCase;
import org.esgi.boissipay.kafka.KafkaException;
import org.esgi.boissipay.kafka.schema.NewContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public final class ContractConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ContractConsumer.class);
    private final CreatePaymentUseCase createPaymentUseCase;
    private final ObjectMapper mapper;

    public ContractConsumer(CreatePaymentUseCase createPaymentUseCase, ObjectMapper objectMapper) {
        this.createPaymentUseCase = createPaymentUseCase;
        mapper = objectMapper;
    }

    @KafkaListener(topics = "#{'${kafka.topic.create-contract}'.split(',')}")
    public void consume(String message) {
        logger.info("Received message: " + message);
        NewContract newContract;
        try {
            newContract = mapper.readValue(message, NewContract.class);
        } catch (JsonProcessingException e) {
            throw new KafkaException(e);
        }
        createPaymentUseCase.createPayment(newContract);
    }


}
