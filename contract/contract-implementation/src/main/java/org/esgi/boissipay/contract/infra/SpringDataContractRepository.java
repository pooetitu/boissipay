package org.esgi.boissipay.contract.infra;

import org.esgi.boissipay.contract.domain.Contract;
import org.esgi.boissipay.contract.domain.ContractRepository;
import org.esgi.boissipay.contract.kernel.ContractMapper;

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
        var contractEntity = jpaContractRepository.findById(contractId);
        return contractEntity.map(ContractMapper::toContract).orElse(null);
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
