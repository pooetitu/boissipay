package org.esgi.boissipay.payment.domain;

public interface PaymentHandler {
    void onProcessPayment(Payment payment);
}
