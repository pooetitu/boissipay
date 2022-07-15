package org.esgi.boissipay.billing.kernel;

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
            .setBilled(payment.billed())
            .setCreatedAt(payment.createdAt())
            .setId(payment.id())
            .setPayedAt(payment.payedAt())
            .setInvoiceRef(payment.invoiceRef());
    }

    public static Payment toPayment(PaymentEntity paymentEntity) {
        return new Payment(
            paymentEntity.id(),
            null,
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
        return new Payment(
            paymentSuccess.paymentId(), null, false, null, null, null
        );
    }

    public static Payment toPayment(String paymentId, String invoiceRef) {
        return new Payment(
            paymentId, null, false, null, null, invoiceRef
        );
    }
}