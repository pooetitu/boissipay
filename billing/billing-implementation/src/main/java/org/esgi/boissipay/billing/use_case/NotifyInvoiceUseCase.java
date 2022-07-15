package org.esgi.boissipay.billing.use_case;

import org.esgi.boissipay.billing.domain.CreatedInvoiceHandler;
import org.esgi.boissipay.billing.domain.Invoice;

public class NotifyInvoiceUseCase implements CreatedInvoiceHandler {
    @Override
    public void onInvoiceCreated(Invoice invoice) {
        System.out.println("Send invoice to contact "+ invoice.contactPerson().mail());
    }
}
