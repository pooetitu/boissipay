package org.esgi.boissipay.contract.domain;

import java.util.List;

public interface ContractRepository {
    void save(Contract contract);

    Contract getContract(String contractId);

    Contract getContractByRef(String contractRef);

    List<Contract> getContracts();

    List<Contract> getActiveContracts();

    void delete(Contract contract);
}
