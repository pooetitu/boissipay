package org.esgi.boissipay.billing.kernel;

import org.esgi.boissipay.billing.domain.models.Order;
import org.esgi.boissipay.billing.infra.entity.OrderEntity;
import org.esgi.boissipay.billing.model.OperationRequestOrder;
import org.esgi.boissipay.billing.model.OperationRequestOrderOrderItemsInner;
import org.esgi.boissipay.billing.model.OperationRequestOrderOrderItemsInnerProductItemsInner;

import java.math.BigDecimal;
import java.util.List;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderEntity toOrderEntity(Order order) {
        return new OrderEntity()
            .setOrderRef(order.orderRef())
            .setOrderType(order.orderType())
            .setPriceTax(order.priceTax())
            .setOperationId(order.operationId())
            .setPriceWithoutTax(order.priceWithoutTax())
            .setPriceWithTax(order.priceWithTax())
            .setProductLabel(order.productLabel())
            .setProductRef(order.productRef())
            .setQuantity(order.quantity());
    }

    public static Order toOrder(OrderEntity orderEntity) {
        return new Order(
            orderEntity.orderRef(),
            orderEntity.operationId(),
            orderEntity.orderType(),
            orderEntity.productRef(),
            orderEntity.productLabel(),
            orderEntity.quantity(),
            orderEntity.priceWithoutTax(),
            orderEntity.priceTax(),
            orderEntity.priceWithTax()
        );
    }

    public static List<Order> toOrder(OperationRequestOrder order) {
        return order.getOrderItems().stream()
            .map(orderItem -> new Order(
                order.getOrderRef(),
                null,
                order.getOrderType().getValue(),
                orderItem.getProductItems().get(0).getProductRef(),
                orderItem.getProductItems().get(0).getProductLabel(),
                orderItem.getProductItems().stream().mapToInt(OperationRequestOrderOrderItemsInnerProductItemsInner::getQuantity).sum(),
                orderItem.getProductItems().stream().mapToDouble(productItem -> productItem.getAmountWithoutTax().doubleValue()).sum(),
                orderItem.getProductItems().stream().mapToDouble(productItem -> productItem.getAmountTax().doubleValue()).sum(),
                orderItem.getProductItems().stream().mapToDouble(productItem -> productItem.getAmountWithTax().doubleValue()).sum()
            ))
            .toList();
    }

    public static OperationRequestOrder toOperationRequestOrder(List<Order> orders) {
        var response = new OperationRequestOrder();
        if (orders.isEmpty()) {
            return response;
        }
        response.setOrderRef(orders.get(0).orderRef());
        response.setOrderType(OperationRequestOrder.OrderTypeEnum.valueOf(orders.get(0).orderType()));
        response.setOrderItems(orders.stream()
            .map(order -> {
                    var orderItem = new OperationRequestOrderOrderItemsInner();
                    var productItem = new OperationRequestOrderOrderItemsInnerProductItemsInner();
                    productItem.setProductLabel(order.productLabel());
                    productItem.setProductRef(order.productRef());
                    productItem.setQuantity(order.quantity());
                    productItem.setAmountWithoutTax(BigDecimal.valueOf(order.priceWithoutTax()));
                    productItem.setAmountTax(BigDecimal.valueOf(order.priceTax()));
                    productItem.setAmountWithTax(BigDecimal.valueOf(order.priceWithTax()));
                    orderItem.setProductItems(List.of(productItem));
                    return orderItem;
                }
            )
            .toList()
        );
        return response;
    }
}
