package org.esgi.boissipay.billing.kernel;

import org.esgi.boissipay.billing.domain.models.ContactPerson;
import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.infra.entity.OperationEntity;
import org.esgi.boissipay.billing.infra.entity.OrderEntity;
import org.esgi.boissipay.billing.model.OperationRequest;
import org.esgi.boissipay.billing.model.OperationResponse;

import java.util.List;

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

    public static Operation toOperation(OperationEntity operationEntity, List<OrderEntity> orderEntities) {
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
            orderEntities.stream().map(OrderMapper::toOrder).toList()
        );
    }

    public static Operation toOperation(OperationRequest operationRequest) {
        return new Operation(
            null,
            operationRequest.getContractId(),
            new ContactPerson(
                operationRequest.getContact().getCcuid(),
                operationRequest.getContact().getGender().getValue(),
                operationRequest.getContact().getFirstName(),
                operationRequest.getContact().getLastName(),
                operationRequest.getContact().getMail(),
                operationRequest.getContact().getPhone()
            ),
            OrderMapper.toOrder(operationRequest.getOrder())
        );
    }

    public static OperationResponse toOperationResponse(Operation operation) {
        var response = new OperationResponse();
        response.setOperationId(operation.id());
        response.setContractId(operation.contractId());
        response.setOrder(OrderMapper.toOperationRequestOrder(operation.orders()));
        return response;
    }
}
