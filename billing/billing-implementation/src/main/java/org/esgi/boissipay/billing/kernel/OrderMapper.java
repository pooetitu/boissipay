package org.esgi.boissipay.billing.kernel;

import org.esgi.boissipay.billing.domain.models.Order;
import org.esgi.boissipay.billing.infra.entity.OrderEntity;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderEntity toOrderEntity(Order order) {
        return new OrderEntity()
            .setOrderRef(order.orderRef())
            .setOrderType(order.orderType())
            .setPriceTax(order.priceTax())
            .setPriceWithoutTax(order.priceWithoutTax())
            .setPriceWithTax(order.priceWithTax())
            .setProductLabel(order.productLabel())
            .setProductRef(order.productRef())
            .setQuantity(order.quantity());
    }

    public static Order toOrder(OrderEntity orderEntity) {
        return new Order(
            orderEntity.orderRef(),
            orderEntity.operation().id(),
            orderEntity.orderType(),
            orderEntity.productRef(),
            orderEntity.productLabel(),
            orderEntity.quantity(),
            orderEntity.priceWithoutTax(),
            orderEntity.priceTax(),
            orderEntity.priceWithTax()
        );
    }
}
