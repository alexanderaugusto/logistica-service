package br.inatel.dm112.interfaces;

import java.util.List;

import br.inatel.dm112.model.DeliveryStatus;
import br.inatel.dm112.model.Order;

public interface Logistics {

    DeliveryStatus startDeliveryOfOrder(Order order);

    DeliveryStatus confirmDeliveryOfOrder(Order order);

    List<Order> listOrdersToDelivery();

}
