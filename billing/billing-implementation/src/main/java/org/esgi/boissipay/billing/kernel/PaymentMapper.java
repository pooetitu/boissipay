package org.esgi.boissipay.billing.kernel;

import org.esgi.boissipay.billing.domain.models.Payment;
import org.esgi.boissipay.billing.infra.entity.PaymentEntity;

public class PaymentMapper {
    public static PaymentEntity toPaymentEntity(Payment payment) {
        return new PaymentEntity()
            .setBilled(payment.billed())
            .setCreatedAt(payment.createdAt())
            .setId(payment.id())
            .setPayedAt(payment.payedAt());
    }

    public static Payment toPayment(PaymentEntity paymentEntity) {
        return new Payment(
            paymentEntity.id(),
            OperationMapper.toOperation(paymentEntity.operation()),
            paymentEntity.billed(),
            paymentEntity.createdAt(),
            paymentEntity.payedAt()
        );
    }
}
