package org.esgi.boissipay.billing.use_case;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.billing.domain.PaymentRepository;
import org.esgi.boissipay.billing.domain.Payment;
import org.esgi.boissipay.billing.exposition.CreatePaymentRequest;
import org.esgi.boissipay.billing.infra.PaymentFactory;
import org.esgi.boissipay.billing.infra.EventDispatcher;
import org.esgi.boissipay.kafka.KafkaException;
import org.esgi.boissipay.kafka.schema.NewContract;

import java.io.IOException;
import java.util.Objects;

public final class CreatePaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final ObjectMapper mapper;
    private final EventDispatcher eventDispatcher;

    public CreatePaymentUseCase(PaymentRepository paymentRepository, ObjectMapper objectMapper, EventDispatcher eventDispatcher) {
        this.paymentRepository = Objects.requireNonNull(paymentRepository);
        this.mapper = Objects.requireNonNull(objectMapper);
        this.eventDispatcher = eventDispatcher;
    }

    public void createBilling(String message) {
        var billing = buildBillingFromMessage(message);
        var createBillingRequest = new CreatePaymentRequest(billing.instant(), billing.contractName());
        eventDispatcher.dispatchCreateBilling(createBillingRequest);
    }

    private Payment buildBillingFromMessage(String message) {
        NewContract newContract;
        try {
            newContract = mapper.readValue(message, NewContract.class);
        } catch(IOException ex) {
            throw new KafkaException(ex);
        }
        Payment payment = PaymentFactory.createFrom(newContract);
        paymentRepository.save(payment);
        return payment;
    }
}
