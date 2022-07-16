package org.esgi.boissipay.contract.use_case;

import org.esgi.boissipay.contract.domain.Contract;
import org.esgi.boissipay.contract.domain.ContractRepository;

import java.util.List;

public class GetActiveContractsUseCase {

    private final ContractRepository contractRepository;

    public GetActiveContractsUseCase(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> getContracts() {
        return contractRepository.getActiveContracts();
    }
}
