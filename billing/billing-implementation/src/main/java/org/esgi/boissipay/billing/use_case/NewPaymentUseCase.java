package org.esgi.boissipay.billing.use_case;

import org.esgi.boissipay.billing.domain.event.EventDispatcher;
import org.esgi.boissipay.billing.domain.models.Payment;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public final class NewPaymentUseCase {

    private final PaymentRepository paymentRepository;

    private final EventDispatcher eventDispatcher;

    public NewPaymentUseCase(PaymentRepository paymentRepository, EventDispatcher eventDispatcher) {
        this.paymentRepository = Objects.requireNonNull(paymentRepository);
        this.eventDispatcher = eventDispatcher;
    }

    public void newPayment(Payment newPayment) {
        if (newPayment.id() == null) {
            newPayment.setId(UUID.randomUUID().toString());
        }
        newPayment.setCreatedAt(LocalDate.now());

        paymentRepository.save(newPayment);
        eventDispatcher.dispatchProcessPayment(newPayment);
    }
}
