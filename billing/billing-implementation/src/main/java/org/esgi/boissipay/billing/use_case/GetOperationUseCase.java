package org.esgi.boissipay.billing.use_case;

import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.domain.repository.OperationRepository;

public final class GetOperationUseCase {

    private final OperationRepository operationRepository;

    public GetOperationUseCase(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Operation getOperation(String operationId) {
        return operationRepository.getOperation(operationId);
    }
}
