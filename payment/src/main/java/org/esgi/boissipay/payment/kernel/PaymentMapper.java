package org.esgi.boissipay.payment.kernel;

import org.esgi.boissipay.kafka.schema.PaymentSuccess;
import org.esgi.boissipay.kafka.schema.ProcessPayment;
import org.esgi.boissipay.payment.domain.Payment;

public class PaymentMapper {

    public static Payment toPayment(ProcessPayment processPayment) {
        return new Payment(
            processPayment.paymentId(),
            processPayment.operationId(),
            processPayment.personnCcuid(),
            processPayment.priceWithoutTax(),
            processPayment.priceTax(),
            processPayment.priceWithTax()
        );
    }

    public static PaymentSuccess toPaymentSuccess(Payment payment) {
        return new PaymentSuccess(
            payment.paymentId(),
            payment.operationId(),
            payment.personnCcuid()
        );
    }
}
