package org.esgi.boissipay.invoice.infra;

import org.esgi.boissipay.invoice.domain.EmailHandler;
import org.esgi.boissipay.invoice.domain.Invoice;
import org.esgi.boissipay.invoice.kafka.InvoiceSentProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultEmailHandler implements EmailHandler {
    private static final Logger logger = LoggerFactory.getLogger(DefaultEmailHandler.class);

    private final InvoiceSentProducer invoiceSentProducer;

    public DefaultEmailHandler(InvoiceSentProducer invoiceSentProducer) {
        this.invoiceSentProducer = invoiceSentProducer;
    }

    @Override
    public void onInvoiceSend(Invoice invoice) {
        logger.info("Sending invoice: " + invoice);
        logger.info("Success !");
        invoiceSentProducer.onInvoiceSent(invoice);
    }
}
