package org.esgi.boissipay.billing.infra;

import org.esgi.boissipay.billing.domain.CreatedBillingListener;
import org.esgi.boissipay.billing.exposition.CreateBillingRequest;

import java.util.Set;

public final class EventDispatcher {
    private final Set<CreatedBillingListener> createdBillListeners;

    public EventDispatcher(Set<CreatedBillingListener> createdBillListeners) {
        this.createdBillListeners = createdBillListeners;
    }

    public void dispatchCreateBilling(CreateBillingRequest request) {
        for (var listener : createdBillListeners) {
            listener.onBillCreated(request);
        }
    }
}
