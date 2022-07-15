package org.esgi.boissipay.billing.use_case.handler;

import org.esgi.boissipay.billing.domain.event.NewOperationHandler;
import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.use_case.NewOperationUseCase;

public class NewOperationEventHandler implements NewOperationHandler {
    private final NewOperationUseCase newOperationUseCase;

    public NewOperationEventHandler(NewOperationUseCase newOperationUseCase) {
        this.newOperationUseCase = newOperationUseCase;
    }

    @Override
    public void onNewOperation(Operation operation) {
        newOperationUseCase.newOperation(operation);
    }
}
