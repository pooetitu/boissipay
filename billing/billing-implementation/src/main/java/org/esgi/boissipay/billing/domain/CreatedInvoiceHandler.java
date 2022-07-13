package org.esgi.boissipay.billing.domain;


public interface CreatedInvoiceHandler {
    void onInvoiceCreated(Invoice invoice);
}
