package org.esgi.boissipay.billing.kernel;

import org.esgi.boissipay.billing.domain.models.ContactPerson;
import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.infra.entity.OperationEntity;

public class OperationMapper {

    private OperationMapper() {
    }

    public static OperationEntity toOperationEntity(Operation operation) {
        return new OperationEntity()
            .setId(operation.id())
            .setContractId(operation.contractId())
            .setPersonCcuid(operation.contactPerson().ccuid())
            .setPersonGender(operation.contactPerson().gender())
            .setPersonFirstName(operation.contactPerson().firstName())
            .setPersonLastName(operation.contactPerson().lastName())
            .setPersonEmail(operation.contactPerson().email())
            .setPersonPhone(operation.contactPerson().phone());
    }

    public static Operation toOperation(OperationEntity operationEntity) {
        return new Operation(
            operationEntity.id(),
            operationEntity.contractId(),
            new ContactPerson(
                operationEntity.personCcuid(),
                operationEntity.personGender(),
                operationEntity.personFirstName(),
                operationEntity.personLastName(),
                operationEntity.personEmail(),
                operationEntity.personPhone()
            ),
            null
        );
    }
}
