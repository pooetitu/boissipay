package org.esgi.boissipay.billing.use_case;

import org.esgi.boissipay.billing.domain.models.Contract;
import org.esgi.boissipay.billing.domain.repository.ContractRepository;

public final class NewContractUseCase {

    private final ContractRepository contractRepository;

    public NewContractUseCase(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public void newContract(Contract newContract) {
        contractRepository.save(newContract);
    }
}
