package org.esgi.boissipay.billing.kafka;

import org.esgi.boissipay.billing.infra.InMemoryBillingRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsumerTest {

    private InMemoryBillingRepository repo = InMemoryBillingRepository.newEmptyInMemoryBillingRepository();
    private Consumer consumer = new Consumer(repo, null);

    @Test
    void test_consume() {
        var kafkaInput = "{\"name\": \"test\"}";
        consumer.consume(kafkaInput);
        assertEquals(repo.count(), 1);
    }
}
