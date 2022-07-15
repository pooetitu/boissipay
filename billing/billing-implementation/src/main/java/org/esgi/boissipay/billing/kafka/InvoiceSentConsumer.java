package org.esgi.boissipay.billing.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.billing.domain.EventDispatcher;
import org.esgi.boissipay.billing.kernel.InvoiceMapper;
import org.esgi.boissipay.billing.kernel.PaymentMapper;
import org.esgi.boissipay.kafka.KafkaException;
import org.esgi.boissipay.kafka.schema.InvoiceSent;
import org.esgi.boissipay.kafka.schema.PaymentSuccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public final class InvoiceSentConsumer {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceSentConsumer.class);
    private final EventDispatcher eventDispatcher;
    private final ObjectMapper mapper;

    public InvoiceSentConsumer(EventDispatcher eventDispatcher, ObjectMapper mapper) {
        this.eventDispatcher = eventDispatcher;
        this.mapper = mapper;
    }


    @KafkaListener(topics = "${kafka.topic.invoice-sent}")
    public void consume(String message) {
        logger.info("Received invoice sent message: " + message);
        InvoiceSent invoiceSent;
        try {
            invoiceSent = mapper.readValue(message, InvoiceSent.class);
        } catch (JsonProcessingException e) {
            throw new KafkaException(e);
        }
        eventDispatcher.dispatchInvoiceSent(InvoiceMapper.toInvoice(invoiceSent));
    }


}
