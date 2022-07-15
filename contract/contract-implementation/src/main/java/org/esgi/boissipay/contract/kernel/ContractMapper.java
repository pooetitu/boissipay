package org.esgi.boissipay.contract.kernel;

import org.esgi.boissipay.contract.domain.ContactPerson;
import org.esgi.boissipay.contract.domain.Contract;
import org.esgi.boissipay.contract.domain.Customer;
import org.esgi.boissipay.contract.domain.Subscriber;
import org.esgi.boissipay.contract.infra.ContractEntity;
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
            SubscriberMapper.toKafkaSubscriber(contract.subscriber()),
            contract.status()
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

    public static Contract toContract(ContractEntity contractEntity) {
        return new Contract()
            .setContractId(contractEntity.id())
            .setContractRef(contractEntity.ref())
            .setContractType(contractEntity.type())
            .setStatus(contractEntity.status())
            .setCreatedAt(contractEntity.createdAt())
            .setActivatedAt(contractEntity.activatedAt())
            .setExpireAt(contractEntity.expireAt())
            .setSubscriber(
                new Subscriber(
                    contractEntity.subscriberRef(),
                    contractEntity.subscriberType(),
                    new Customer(
                        contractEntity.customerId(),
                        contractEntity.customerRef()
                    ),
                    new ContactPerson(
                        contractEntity.contactCcuid(),
                        contractEntity.contactGender(),
                        contractEntity.contactFirstName(),
                        contractEntity.contactLastName(),
                        contractEntity.contactEmail(),
                        contractEntity.contactPhone()
                    )
                )

            );
    }

    public static ContractEntity toContractEntity(Contract contract) {
        return new ContractEntity(
            contract.contractId(),
            contract.contractRef(),
            contract.contractType(),
            contract.createdAt(),
            contract.activatedAt(),
            contract.expireAt(),
            contract.status(),
            contract.subscriber().subscriberRef(),
            contract.subscriber().subscriberType(),
            contract.subscriber().customer().customerId(),
            contract.subscriber().customer().customerRef(),
            contract.subscriber().contactPerson().ccuid(),
            contract.subscriber().contactPerson().gender(),
            contract.subscriber().contactPerson().firstName(),
            contract.subscriber().contactPerson().lastName(),
            contract.subscriber().contactPerson().email(),
            contract.subscriber().contactPerson().phone()
        );

    }
}
