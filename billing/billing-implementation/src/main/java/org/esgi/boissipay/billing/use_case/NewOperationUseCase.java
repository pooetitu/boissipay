package org.esgi.boissipay.billing.use_case;

import org.esgi.boissipay.billing.domain.event.EventDispatcher;
import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.domain.repository.OperationRepository;

import java.util.UUID;

public final class NewOperationUseCase {

    private final NewOrderUseCase newOrderUseCase;

    private final OperationRepository operationRepository;

    private final EventDispatcher eventDispatcher;

    public NewOperationUseCase(NewOrderUseCase newOrderUseCase, OperationRepository operationRepository, EventDispatcher eventDispatcher) {
        this.newOrderUseCase = newOrderUseCase;
        this.operationRepository = operationRepository;
        this.eventDispatcher = eventDispatcher;
    }

    public void newOperation(Operation newOperation) {
        if (newOperation.id() == null) {
            newOperation.setId(UUID.randomUUID().toString());
        }
        operationRepository.save(newOperation);
        newOperation.orders().forEach(
            newOrder -> newOrderUseCase.newOrder(newOrder.setOperationId(newOperation.id()))
        );
        eventDispatcher.dispatchNewOperation(newOperation);
    }
}
