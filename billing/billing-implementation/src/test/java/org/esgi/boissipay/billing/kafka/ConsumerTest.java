package org.esgi.boissipay.billing.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.esgi.boissipay.billing.infra.EventDispatcher;
import org.esgi.boissipay.billing.infra.InMemoryBillingRepository;
import org.esgi.boissipay.billing.use_case.CreateBillingUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ConsumerTest {

    private final InMemoryBillingRepository repo = InMemoryBillingRepository.newEmptyInMemoryBillingRepository();
    private final static ObjectMapper mapper = new ObjectMapper();
    private final CreateBillingUseCase createBillingUseCase = new CreateBillingUseCase(repo, mapper, new EventDispatcher(Collections.emptySet()));
    private final Consumer consumer = new Consumer(createBillingUseCase);

    @BeforeAll
    static void beforeAll() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void test_consume() {
        var kafkaInput = "{\"name\": \"test\"}";
        consumer.consume(kafkaInput);
        assertEquals(repo.count(), 1);
    }
}
