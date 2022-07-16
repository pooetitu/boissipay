package org.esgi.boissipay.billing.domain.repository;

import org.esgi.boissipay.billing.domain.models.Operation;

import java.util.List;

public interface OperationRepository {
    void save(Operation operation);

    Operation getOperation(String operationId);

    List<Operation> getOperations();

    List<Operation> getOperationsByContract(String contractId);

    void delete(Operation operation);
}
