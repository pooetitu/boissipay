package org.esgi.boissipay.billing.use_case.handler;

import org.esgi.boissipay.billing.domain.event.CreateContractHandler;
import org.esgi.boissipay.billing.domain.models.Contract;
import org.esgi.boissipay.billing.use_case.NewContractUseCase;

public class CreateContractEventHandler implements CreateContractHandler {

    private final NewContractUseCase newContractUseCase;

    public CreateContractEventHandler(NewContractUseCase newContractUseCase) {
        this.newContractUseCase = newContractUseCase;
    }

    @Override
    public void onCreateContract(Contract contract) {
        newContractUseCase.newContract(contract);
    }
}
