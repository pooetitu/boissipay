package org.esgi.boissipay.billing.use_case;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.boissipay.billing.domain.BillingRepository;
import org.esgi.boissipay.billing.exposition.CreateBillingRequest;
import org.esgi.boissipay.billing.infra.BillingFactory;
import org.esgi.boissipay.billing.infra.EventDispatcher;
import org.esgi.boissipay.billing.domain.Billing;
import org.esgi.boissipay.kafka.KafkaException;
import org.esgi.boissipay.kafka.schema.NewContract;

import java.io.IOException;
import java.util.Objects;

public final class CreateBillingUseCase {

    private final BillingRepository billingRepository;
    private final ObjectMapper mapper;
    private final EventDispatcher eventDispatcher;

    public CreateBillingUseCase(BillingRepository billingRepository, ObjectMapper objectMapper, EventDispatcher eventDispatcher) {
        this.billingRepository = Objects.requireNonNull(billingRepository);
        this.mapper = Objects.requireNonNull(objectMapper);
        this.eventDispatcher = eventDispatcher;
    }

    public void createBilling(String message) {
        var billing = buildBillingFromMessage(message);
        var createBillingRequest = new CreateBillingRequest(billing.instant(), billing.contractName());
        eventDispatcher.dispatchCreateBilling(createBillingRequest);
    }

    private Billing buildBillingFromMessage(String message) {
        NewContract newContract = null;
        try {
            newContract = mapper.readValue(message, NewContract.class);
        } catch(IOException ex) {
            throw new KafkaException(ex);
        }
        Billing billing = BillingFactory.createFrom(newContract);
        billingRepository.save(billing);
        return billing;
    }
}
