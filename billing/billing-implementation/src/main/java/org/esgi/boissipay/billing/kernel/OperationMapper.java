package org.esgi.boissipay.billing.kernel;

import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.infra.entity.OperationEntity;

public class OperationMapper {

    private OperationMapper() {
    }

    public static OperationEntity toOperationEntity(Operation operation) {
        return new OperationEntity()
            .setCustomerRef(operation.customerRef())
            .setId(operation.id());
    }

    public static Operation toOperation(OperationEntity operationEntity) {
        return new Operation(
            operationEntity.id(),
            operationEntity.customerRef(),
            operationEntity.payment().id(),
            operationEntity.orders().stream().map(OrderMapper::toOrder).toList()
        );
    }
}
