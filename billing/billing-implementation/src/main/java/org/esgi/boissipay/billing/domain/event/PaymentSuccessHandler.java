package org.esgi.boissipay.billing.domain.event;

import org.esgi.boissipay.billing.domain.models.Payment;

public interface PaymentSuccessHandler {
    void onPaymentSuccess(Payment payment);
}
