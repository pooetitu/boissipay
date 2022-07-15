package org.esgi.boissipay.billing.use_case;

import org.esgi.boissipay.billing.domain.repository.PaymentRepository;

public class SetPaymentInvoiceRefUseCase {
    private final PaymentRepository paymentRepository;

    public SetPaymentInvoiceRefUseCase(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void setInvoiceRef(String paymentId, String invoiceRef) {
        var payment = paymentRepository.getPayment(paymentId);
        if (payment == null) {
            throw new IllegalArgumentException("Payment not found");
        }
        payment.setInvoiceRef(invoiceRef);
        paymentRepository.save(payment);
    }
}
