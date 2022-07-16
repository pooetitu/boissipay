package org.esgi.boissipay.billing.use_case;

import org.esgi.boissipay.billing.domain.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Objects;

public final class ValidatePaymentUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ValidatePaymentUseCase.class);

    private final PaymentRepository paymentRepository;

    public ValidatePaymentUseCase(PaymentRepository paymentRepository) {
        this.paymentRepository = Objects.requireNonNull(paymentRepository);
    }

    public void validatePayment(String paymentId) {
        var payment = paymentRepository.getPayment(paymentId);
        if (payment == null) {
            logger.error("Payment not found");
            throw new IllegalArgumentException("Payment not found");
        }
        payment.setPayedAt(LocalDate.now());
        paymentRepository.save(payment);
    }
}
