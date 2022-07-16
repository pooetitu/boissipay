package org.esgi.boissipay.billing.use_case;

import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.domain.models.Payment;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;

import java.util.List;

public final class GetOperationsByInvoiceRefUseCase {

    private final PaymentRepository paymentRepository;

    public GetOperationsByInvoiceRefUseCase(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public List<Operation> getOperations(String invoiceRef) {
        return paymentRepository.getByInvoiceRef(invoiceRef)
            .stream().map(Payment::operation).toList();
    }
}
