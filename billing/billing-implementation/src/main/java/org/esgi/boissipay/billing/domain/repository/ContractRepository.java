package org.esgi.boissipay.billing.domain.repository;

import org.esgi.boissipay.billing.domain.models.Contract;

import java.util.List;

public interface ContractRepository {
    void save(Contract contract);

    Contract getContract(String contractId);

    List<Contract> getContracts();

    List<Contract> getActiveContracts();

    void delete(Contract contrat);
}
