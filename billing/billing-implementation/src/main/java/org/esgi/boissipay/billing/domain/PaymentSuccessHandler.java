package org.esgi.boissipay.billing.domain;

import org.esgi.boissipay.billing.domain.models.Payment;

public interface PaymentSuccessHandler {
    void onPaymentSuccess(Payment payment);
}
