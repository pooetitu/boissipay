package org.esgi.boissipay.billing.domain;

import org.esgi.boissipay.billing.exposition.CreateBillingRequest;

public interface CreatedBillingListener {
    void onBillCreated(CreateBillingRequest request);
}
