package org.esgi.boissipay.contract.domain;

import java.util.Set;

public interface EventDispatcher {
    void dispatchCreateContract(Contract contract);
}
