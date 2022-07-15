package org.esgi.boissipay.billing.use_case;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;
import org.esgi.boissipay.billing.infra.EventDispatcher;
import org.esgi.boissipay.kafka.schema.NewContract;

import java.util.Objects;

public final class CreatePaymentUseCase {

    private final PaymentRepository paymentBakRepository;
    private final ObjectMapper mapper;
    private final EventDispatcher eventDispatcher;

    public CreatePaymentUseCase(PaymentRepository paymentBakRepository, ObjectMapper objectMapper, EventDispatcher eventDispatcher) {
        this.paymentBakRepository = Objects.requireNonNull(paymentBakRepository);
        this.mapper = Objects.requireNonNull(objectMapper);
        this.eventDispatcher = eventDispatcher;
    }

    public void createPayment(NewContract message) {
//        var createPaymentRequest = new CreatePaymentRequest(billing.instant(), billing.contractName());
//        eventDispatcher.dispatchCreatePayment(createPaymentRequest);
    }
}
