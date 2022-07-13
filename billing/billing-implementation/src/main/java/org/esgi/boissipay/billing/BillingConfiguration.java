package org.esgi.boissipay.billing;

import org.esgi.boissipay.billing.infra.InMemoryBillingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillingConfiguration {

    @Bean
    public BillingRepository billingRepository() {
        return InMemoryBillingRepository.newEmptyInMemoryBillingRepository();
    }


}
