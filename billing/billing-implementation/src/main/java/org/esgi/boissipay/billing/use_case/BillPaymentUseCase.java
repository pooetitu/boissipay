package org.esgi.boissipay.billing.use_case;

import org.esgi.boissipay.billing.domain.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public final class BillPaymentUseCase {
    private static final Logger logger = LoggerFactory.getLogger(BillPaymentUseCase.class);

    private final PaymentRepository paymentRepository;

    public BillPaymentUseCase(PaymentRepository paymentRepository) {
        this.paymentRepository = Objects.requireNonNull(paymentRepository);
    }

    public void validatePayment(String paymentId) {
        var payment = paymentRepository.getPayment(paymentId);
        if (payment == null) {
            logger.error("Payment not found");
            throw new IllegalArgumentException("Payment not found");
        }
        payment.setBilled(true);
        paymentRepository.save(payment);
    }
}
