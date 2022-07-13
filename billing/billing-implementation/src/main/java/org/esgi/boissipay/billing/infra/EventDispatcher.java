package org.esgi.boissipay.billing.infra;

import org.esgi.boissipay.billing.domain.CreatedPaymentHandler;
import org.esgi.boissipay.billing.exposition.CreatePaymentRequest;

import java.util.Set;

public final class EventDispatcher {
    private final Set<CreatedPaymentHandler> createdBillListeners;

    public EventDispatcher(Set<CreatedPaymentHandler> createdBillListeners) {
        this.createdBillListeners = createdBillListeners;
    }

    public void dispatchCreateBilling(CreatePaymentRequest request) {
        for (var listener : createdBillListeners) {
            listener.onBillCreated(request);
        }
    }
}
