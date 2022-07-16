package org.esgi.boissipay.billing.kernel;

import org.esgi.boissipay.billing.domain.models.Contract;
import org.esgi.boissipay.billing.infra.entity.ContractEntity;
import org.esgi.boissipay.kafka.schema.NewContract;

public class ContractMapper {

    private ContractMapper() {
    }

    public static ContractEntity toContractEntity(Contract contract) {
        return new ContractEntity()
            .setId(contract.id())
            .setRef(contract.ref())
            .setType(contract.type())
            .setCreatedAt(contract.createdAt())
            .setActivatedAt(contract.activatedAt())
            .setExpireAt(contract.expireAt())
            .setStatus(contract.status());
    }

    public static Contract toContract(ContractEntity contractEntity) {
        return new Contract(
            contractEntity.id(),
            contractEntity.ref(),
            contractEntity.type(),
            contractEntity.createdAt(),
            contractEntity.activatedAt(),
            contractEntity.expireAt(),
            contractEntity.status()
        );
    }

    public static Contract toContract(NewContract newContract) {
        return new Contract(
            newContract.contractId(),
            newContract.contractRef(),
            newContract.contractType(),
            newContract.createdAt(),
            newContract.activatedAt(),
            newContract.expireAt(),
            newContract.status()
        );
    }
}
