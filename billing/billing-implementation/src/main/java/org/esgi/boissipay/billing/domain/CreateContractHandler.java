package org.esgi.boissipay.billing.domain;

import org.esgi.boissipay.billing.domain.models.Contract;

public interface CreateContractHandler {
    void onCreateContract(Contract contract);
}
