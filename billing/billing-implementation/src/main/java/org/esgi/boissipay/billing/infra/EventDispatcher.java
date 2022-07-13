package org.esgi.boissipay.billing.infra;

import org.esgi.boissipay.billing.domain.CreatedInvoiceHandler;
import org.esgi.boissipay.billing.domain.CreatedPaymentHandler;
import org.esgi.boissipay.billing.domain.Invoice;
import org.esgi.boissipay.billing.exposition.CreatePaymentRequest;

import java.util.Set;

public final class EventDispatcher {
    private final Set<CreatedPaymentHandler> createdBillListeners;
    private final Set<CreatedInvoiceHandler> createdInvoiceHandlers;

    public EventDispatcher(Set<CreatedPaymentHandler> createdBillListeners, Set<CreatedInvoiceHandler> createdInvoiceHandlers) {
        this.createdBillListeners = createdBillListeners;
        this.createdInvoiceHandlers = createdInvoiceHandlers;
    }

    public void dispatchCreateBilling(CreatePaymentRequest request) {
        for (var listener : createdBillListeners) {
            listener.onBillCreated(request);
        }
    }

    public void dispatchCreateInvoice(Invoice invoice) {
        for (var listener : createdInvoiceHandlers) {
            listener.onInvoiceCreated(invoice);
        }
    }
}
