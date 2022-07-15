package org.esgi.boissipay.billing.domain.event;

import org.esgi.boissipay.billing.domain.models.Invoice;

public interface InvoiceSentHandler {
    void onInvoiceSent(Invoice invoice);
}
