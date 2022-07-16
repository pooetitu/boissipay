package org.esgi.boissipay.payment.infra;

import org.esgi.boissipay.payment.domain.Payment;
import org.esgi.boissipay.payment.domain.PaymentHandler;
import org.esgi.boissipay.payment.kafka.PaymentSuccessProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultPaymentHandler implements PaymentHandler {
    private static final Logger logger = LoggerFactory.getLogger(DefaultPaymentHandler.class);

    private final PaymentSuccessProducer paymentSuccessProducer;

    public DefaultPaymentHandler(PaymentSuccessProducer paymentSuccessProducer) {
        this.paymentSuccessProducer = paymentSuccessProducer;
    }

    @Override
    public void onProcessPayment(Payment payment) {
        logger.info("Processing payment: " + payment);
        logger.info("Success !");

        paymentSuccessProducer.onPaymentSuccess(payment);
    }
}
