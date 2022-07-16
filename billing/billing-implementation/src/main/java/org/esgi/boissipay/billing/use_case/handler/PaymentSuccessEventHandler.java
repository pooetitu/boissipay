package org.esgi.boissipay.billing.use_case.handler;

import org.esgi.boissipay.billing.domain.event.PaymentSuccessHandler;
import org.esgi.boissipay.billing.domain.models.Payment;
import org.esgi.boissipay.billing.use_case.ValidatePaymentUseCase;

public class PaymentSuccessEventHandler implements PaymentSuccessHandler {
    private final ValidatePaymentUseCase validatePaymentUseCase;

    public PaymentSuccessEventHandler(ValidatePaymentUseCase validatePaymentUseCase) {
        this.validatePaymentUseCase = validatePaymentUseCase;
    }

    @Override
    public void onPaymentSuccess(Payment payment) {
        validatePaymentUseCase.validatePayment(payment.id());
    }
}
