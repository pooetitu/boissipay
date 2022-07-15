package org.esgi.boissipay.billing.infra.repository;

import org.esgi.boissipay.billing.domain.models.Contract;
import org.esgi.boissipay.billing.domain.repository.ContractRepository;
import org.esgi.boissipay.billing.kernel.ContractMapper;

import java.util.List;

public class SpringDataContractRepository implements ContractRepository {
    private final JPAContractRepository jpaContractRepository;

    public SpringDataContractRepository(JPAContractRepository jpaContractRepository) {
        this.jpaContractRepository = jpaContractRepository;
    }

    @Override
    public void save(Contract contract) {
        var contractEntity = ContractMapper.toContractEntity(contract);
        jpaContractRepository.save(contractEntity);
    }

    @Override
    public Contract getContract(String contractId) {
        return jpaContractRepository.findById(contractId).map(ContractMapper::toContract).orElse(null);
    }

    @Override
    public List<Contract> getContracts() {
        return jpaContractRepository.findAll().stream().map(ContractMapper::toContract).toList();
    }

    @Override
    public List<Contract> getActiveContracts() {
        return jpaContractRepository.findByStatusEquals("ACTIVE").stream().map(ContractMapper::toContract).toList();
    }

    @Override
    public void delete(Contract contract) {
        jpaContractRepository.delete(ContractMapper.toContractEntity(contract));
    }
}
