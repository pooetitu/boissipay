package org.esgi.boissipay.billing.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.billing.domain.SendInvoiceHandler;
import org.esgi.boissipay.billing.domain.models.Invoice;
import org.esgi.boissipay.billing.kernel.InvoiceMapper;
import org.esgi.boissipay.kafka.KafkaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;

public final class SendInvoiceProducer implements SendInvoiceHandler {
    private static final Logger logger = LoggerFactory.getLogger(SendInvoiceProducer.class);
    private final String sendInvoiceTopicName;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public SendInvoiceProducer(String sendInvoiceTopicName, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.sendInvoiceTopicName = sendInvoiceTopicName;
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = objectMapper;
    }

    @Override
    public void onSendInvoice(Invoice invoice) {
        logger.info("Sending send invoice event to Kafka");
        String message;
        try {
            message = mapper.writeValueAsString(InvoiceMapper.toKafkaInvoice(invoice));
        } catch (IOException ex) {
            throw new KafkaException(ex);
        }

        kafkaTemplate.send(sendInvoiceTopicName, message);

    }
}
