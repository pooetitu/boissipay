package org.esgi.boissipay.billing.use_case.handler;

import org.esgi.boissipay.billing.domain.event.NewOperationHandler;
import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.domain.models.Payment;
import org.esgi.boissipay.billing.use_case.NewPaymentUseCase;

import java.time.LocalDate;

public class NewOperationEventHandler implements NewOperationHandler {
    private final NewPaymentUseCase newPaymentUseCase;

    public NewOperationEventHandler(NewPaymentUseCase newPaymentUseCase) {
        this.newPaymentUseCase = newPaymentUseCase;
    }

    @Override
    public void onNewOperation(Operation operation) {
        Payment newPayment = new Payment()
            .setBilled(false)
            .setCreatedAt(LocalDate.now())
            .setOperation(operation);
        newPaymentUseCase.newPayment(newPayment);
    }
}
