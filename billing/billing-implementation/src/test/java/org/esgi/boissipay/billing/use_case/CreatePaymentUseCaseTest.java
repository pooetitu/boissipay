package org.esgi.boissipay.billing.use_case;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.esgi.boissipay.billing.infra.EventDispatcher;
import org.esgi.boissipay.billing.infra.InMemoryPaymentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatePaymentUseCaseTest {

    private final InMemoryPaymentRepository repo = InMemoryPaymentRepository.newEmptyInMemoryBillingRepository();
    private final static ObjectMapper mapper = new ObjectMapper();
    private final CreatePaymentUseCase createPaymentUseCase = new CreatePaymentUseCase(repo, mapper, new EventDispatcher(Collections.emptySet()));

    @BeforeAll
    static void beforeAll() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void test_create_billing() {
        var kafkaInput = "{\"name\": \"test\"}";
        createPaymentUseCase.createBilling(kafkaInput);
        assertEquals(repo.count(), 1);
    }
}
