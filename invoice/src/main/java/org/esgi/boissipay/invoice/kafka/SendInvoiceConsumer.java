package org.esgi.boissipay.invoice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.invoice.domain.EmailHandler;
import org.esgi.boissipay.invoice.kernel.InvoiceMapper;
import org.esgi.boissipay.kafka.KafkaException;
import org.esgi.boissipay.kafka.schema.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SendInvoiceConsumer {
    private static final Logger logger = LoggerFactory.getLogger(SendInvoiceConsumer.class);

    private final EmailHandler emailHandler;

    private final ObjectMapper mapper;

    public SendInvoiceConsumer(EmailHandler emailHandler, ObjectMapper mapper) {
        this.emailHandler = emailHandler;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "${kafka.topic.send-invoice}")
    public void consume(String message) {
        logger.info("Received send invoice message: " + message);
        Invoice sendInvoice;
        try {
            sendInvoice = mapper.readValue(message, Invoice.class);
        } catch (JsonProcessingException e) {
            throw new KafkaException(e);
        }
        emailHandler.onInvoiceSend(InvoiceMapper.toInvoice(sendInvoice));
    }
}
