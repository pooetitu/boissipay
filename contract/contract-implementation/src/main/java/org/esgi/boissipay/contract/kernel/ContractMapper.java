package org.esgi.boissipay.contract.kernel;

import org.esgi.boissipay.contract.domain.Contract;
import org.esgi.boissipay.contract.model.ContractRequest;
import org.esgi.boissipay.kafka.schema.NewContract;

import java.util.Objects;

public final class ContractMapper {

    private ContractMapper() {
    }

    public static NewContract toNewContract(Contract contract) {
        return new NewContract(
                contract.contractId(),
                contract.contractRef(),
                contract.contractType(),
                contract.createdAt(),
                contract.activatedAt(),
                contract.expireAt(),
                SubscriberMapper.toKafkaSubscriber(contract.subscriber())
        );
    }

    public static Contract toContract(ContractRequest contractRequest) {
        return new Contract()
                .setContractType(contractRequest.getContractType().getValue())
                .setCreatedAt(contractRequest.getCreatedAt())
                .setActivatedAt(contractRequest.getActivatedAt())
                .setExpireAt(contractRequest.getExpireAt())
                .setStatus(contractRequest.getStatus().getValue())
                .setSubscriber(SubscriberMapper.toSubscriber(Objects.requireNonNull(contractRequest.getSubscriber())));
    }
}
