package org.esgi.boissipay.billing.domain.event;

import org.esgi.boissipay.billing.domain.models.Invoice;

public interface SendInvoiceHandler {
    void onSendInvoice(Invoice invoice);
}
