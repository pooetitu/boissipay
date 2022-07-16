package org.esgi.boissipay.invoice.domain;

public interface EmailHandler {
    void onInvoiceSend(Invoice invoice);
}
