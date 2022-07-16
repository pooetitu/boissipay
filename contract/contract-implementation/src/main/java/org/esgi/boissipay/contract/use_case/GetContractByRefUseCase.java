package org.esgi.boissipay.contract.use_case;

import org.esgi.boissipay.contract.domain.Contract;
import org.esgi.boissipay.contract.domain.ContractRepository;

public class GetContractByRefUseCase {

    private final ContractRepository contractRepository;

    public GetContractByRefUseCase(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract getContract(String contractRef) {
        return contractRepository.getContractByRef(contractRef);
    }
}
