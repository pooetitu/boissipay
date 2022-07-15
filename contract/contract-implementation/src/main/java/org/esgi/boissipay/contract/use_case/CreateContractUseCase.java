package org.esgi.boissipay.contract.use_case;

import org.esgi.boissipay.contract.kernel.ContractMapper;
import org.esgi.boissipay.contract.domain.Contract;
import org.esgi.boissipay.contract.domain.ContractRepository;
import org.esgi.boissipay.contract.domain.EventDispatcher;
import org.esgi.boissipay.contract.model.ContractRequest;

import java.util.UUID;

public class CreateContractUseCase {
    // Save contract in repository + send event to event dispatcher
    private final EventDispatcher eventDispatcher;
    private final ContractRepository contractRepository;

    public CreateContractUseCase(EventDispatcher eventDispatcher, ContractRepository contractRepository) {
        this.eventDispatcher = eventDispatcher;
        this.contractRepository = contractRepository;
    }

    public void createContract(ContractRequest contractRequest) {
        Contract contract = ContractMapper.toContract(contractRequest)
                .setContractId(UUID.randomUUID().toString())
                .setContractRef(UUID.randomUUID().toString());

        contractRepository.save(contract);
        eventDispatcher.dispatchCreateContract(contract);
    }
}
