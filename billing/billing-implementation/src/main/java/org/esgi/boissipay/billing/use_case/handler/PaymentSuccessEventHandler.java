package org.esgi.boissipay.billing.use_case.handler;

import org.esgi.boissipay.billing.domain.event.PaymentSuccessHandler;
import org.esgi.boissipay.billing.domain.models.Payment;
import org.esgi.boissipay.billing.use_case.ValidatePaymentUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentSuccessEventHandler implements PaymentSuccessHandler {
    private final ValidatePaymentUseCase validatePaymentUseCase;

    private static final Logger logger = LoggerFactory.getLogger(PaymentSuccessEventHandler.class);

    public PaymentSuccessEventHandler(ValidatePaymentUseCase validatePaymentUseCase) {
        this.validatePaymentUseCase = validatePaymentUseCase;
    }

    @Override
    public void onPaymentSuccess(Payment payment) {
        logger.info("Payment success event handler: " + payment);
        validatePaymentUseCase.validatePayment(payment.id());
    }
}
