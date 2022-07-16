package org.esgi.boissipay.contract.infra;

import org.esgi.boissipay.contract.domain.ContractRepository;
import org.esgi.boissipay.contract.domain.CreateContractHandler;
import org.esgi.boissipay.contract.domain.EventDispatcher;
import org.esgi.boissipay.contract.kafka.Producer;
import org.esgi.boissipay.contract.use_case.CreateContractUseCase;
import org.esgi.boissipay.contract.use_case.GetActiveContractsUseCase;
import org.esgi.boissipay.contract.use_case.GetContractByRefUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ContractConfiguration {

    @Bean
    ContractRepository contractRepository(JPAContractRepository jpaContractRepository) {
        return new SpringDataContractRepository(jpaContractRepository);
    }

    @Bean
    Set<CreateContractHandler> createContractHandlers(Producer kafkaProducer) {
        var set = new HashSet<CreateContractHandler>();
        set.add(kafkaProducer);
        return set;
    }

    @Bean
    EventDispatcher eventDispatcher(Set<CreateContractHandler> createContractHandlers) {
        return new DefaultEventDispatcher(createContractHandlers);
    }

    @Bean
    CreateContractUseCase createContractUseCase(EventDispatcher eventDispatcher, ContractRepository contractRepository) {
        return new CreateContractUseCase(eventDispatcher, contractRepository);
    }

    @Bean
    GetActiveContractsUseCase getActiveContractsUseCase(ContractRepository contractRepository) {
        return new GetActiveContractsUseCase(contractRepository);
    }

    @Bean
    GetContractByRefUseCase getContractByRefUseCase(ContractRepository contractRepository) {
        return new GetContractByRefUseCase(contractRepository);
    }
}
