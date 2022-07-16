package org.esgi.boissipay.billing.domain.event;

import org.esgi.boissipay.billing.domain.models.Payment;

public interface ProcessPaymentHandler {
    void onProcessPayment(Payment payment);
}
