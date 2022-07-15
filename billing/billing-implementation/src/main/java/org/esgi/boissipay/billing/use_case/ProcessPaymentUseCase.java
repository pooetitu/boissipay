package org.esgi.boissipay.billing.use_case;

import java.util.Objects;

import org.esgi.boissipay.billing.domain.models.Payment;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ProcessPaymentUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ProcessPaymentUseCase.class);

    private final PaymentRepository paymentRepository;

    public ProcessPaymentUseCase(PaymentRepository paymentRepository) {
        this.paymentRepository = Objects.requireNonNull(paymentRepository);
    }

    public void pay(Payment payment) {
        paymentRepository.delete(payment);
        logger.info("Payment {} has been paid", payment);
//        paymentRepository.save(new PaymentBak(payment.instant(), payment.contactPerson(), payment.contractName(), true));
    }
}
