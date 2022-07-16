package org.esgi.boissipay.billing.domain.event;

import org.esgi.boissipay.billing.domain.models.Operation;

public interface NewOperationHandler {
    void onNewOperation(Operation operation);
}
