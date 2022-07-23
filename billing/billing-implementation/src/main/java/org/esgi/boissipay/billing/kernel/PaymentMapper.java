package org.esgi.boissipay.billing.kernel;

import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.domain.models.Order;
import org.esgi.boissipay.billing.domain.models.Payment;
import org.esgi.boissipay.billing.infra.entity.PaymentEntity;
import org.esgi.boissipay.kafka.schema.PaymentSuccess;
import org.esgi.boissipay.kafka.schema.ProcessPayment;

public class PaymentMapper {

    private PaymentMapper() {
    }

    public static PaymentEntity toPaymentEntity(Payment payment) {
        return new PaymentEntity()
            .setId(payment.id())
            .setOperationId(payment.operation().id())
            .setBilled(payment.billed())
            .setCreatedAt(payment.createdAt())
            .setPayedAt(payment.payedAt())
            .setInvoiceRef(payment.invoiceRef());
    }

    public static Payment toPayment(PaymentEntity paymentEntity, Operation operation) {
        return new Payment(
            paymentEntity.id(),
            operation,
            paymentEntity.billed(),
            paymentEntity.createdAt(),
            paymentEntity.payedAt(),
            paymentEntity.invoiceRef()
        );
    }

    public static ProcessPayment toProcessPayment(Payment payment) {
        return new ProcessPayment(
            payment.id(),
            payment.operation().id(),
            payment.operation().contactPerson().ccuid(),
            payment.operation().orders().stream().mapToDouble(Order::priceWithoutTax).sum(),
            payment.operation().orders().stream().mapToDouble(Order::priceTax).sum(),
            payment.operation().orders().stream().mapToDouble(Order::priceWithTax).sum()
        );
    }

    public static Payment toPayment(PaymentSuccess paymentSuccess) {
        return new Payment()
            .setId(paymentSuccess.paymentId());
    }

    public static Payment toPayment(String paymentId, String invoiceRef) {
        return new Payment(
            paymentId, null, false, null, null, invoiceRef
        );
    }
}
