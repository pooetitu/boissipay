package org.esgi.boissipay.billing.domain;

import org.esgi.boissipay.billing.exposition.CreatePaymentRequest;

public interface CreatedPaymentHandler {
    void onBillCreated(CreatePaymentRequest request);
}
