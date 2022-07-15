package org.esgi.boissipay.billing.infra.repository;

import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.domain.repository.OperationRepository;
import org.esgi.boissipay.billing.kernel.OperationMapper;

import java.util.List;

public class SpringDataOperationRepository implements OperationRepository {
    private final JPAOperationRepository jpaOperationRepository;

    public SpringDataOperationRepository(JPAOperationRepository jpaOperationRepository) {
        this.jpaOperationRepository = jpaOperationRepository;
    }

    @Override
    public void save(Operation operation) {
        jpaOperationRepository.save(OperationMapper.toOperationEntity(operation));
    }

    @Override
    public Operation getOperation(String operationId) {
        return jpaOperationRepository.findById(operationId).map(OperationMapper::toOperation).orElse(null);
    }

    @Override
    public List<Operation> getOperations() {
        return jpaOperationRepository.findAll().stream().map(OperationMapper::toOperation).toList();
    }

    @Override
    public List<Operation> getOperationsByContract(String contractId) {
        return jpaOperationRepository.findByContractId(contractId).stream().map(OperationMapper::toOperation).toList();
    }

    @Override
    public void delete(Operation operation) {
        jpaOperationRepository.delete(OperationMapper.toOperationEntity(operation));
    }
}
