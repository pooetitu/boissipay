package org.esgi.boissipay.invoice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.invoice.domain.Invoice;
import org.esgi.boissipay.invoice.kernel.InvoiceMapper;
import org.esgi.boissipay.kafka.KafkaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;

public class InvoiceSentProducer {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceSentProducer.class);

    private final String invoiceSentTopicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper mapper;

    public InvoiceSentProducer(String invoiceSentTopicName, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper mapper) {
        this.invoiceSentTopicName = invoiceSentTopicName;
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
    }

    public void onInvoiceSent(Invoice invoice) {
        logger.info("Sending invoice sent event to Kafka");
        String message;
        try {
            message = mapper.writeValueAsString(InvoiceMapper.toInvoiceSent(invoice));
        } catch (IOException ex) {
            throw new KafkaException(ex);
        }

        kafkaTemplate.send(invoiceSentTopicName, message);
    }
}
