package org.esgi.boissipay.contract.infra;

import org.esgi.boissipay.contract.domain.Contract;
import org.esgi.boissipay.contract.domain.CreateContractHandler;
import org.esgi.boissipay.contract.domain.EventDispatcher;

import java.util.Objects;
import java.util.Set;

public class DefaultEventDispatcher implements EventDispatcher {

    private final Set<CreateContractHandler> createContractHandlers;

    public DefaultEventDispatcher(Set<CreateContractHandler> createContractHandlers) {
        this.createContractHandlers = Objects.requireNonNull(createContractHandlers);
    }

    @Override
    public void dispatchCreateContract(Contract contract) {
        createContractHandlers.forEach(handler -> handler.onContractCreated(contract));
    }
}
