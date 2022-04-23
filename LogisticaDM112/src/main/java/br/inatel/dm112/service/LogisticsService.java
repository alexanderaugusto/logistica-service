package br.inatel.dm112.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.inatel.dm112.client.EmailClient;
import br.inatel.dm112.client.OrderClient;
import br.inatel.dm112.model.DeliveryStatus;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.Order.STATUS;

@Service
public class LogisticsService {

    @Autowired
    private OrderClient clientOrder;

    @Autowired
    private EmailClient clientEmail;

    /**
     * Lógica para inicializar a entrega do pedido
     * (1) consulta o pedido pelo número
     * (2) atualiza o status do pedido
     * (3) retorna sucesso
     *
     * @param orderNumber
     * @return
     */
    public DeliveryStatus startDeliveryOfOrder(int orderNumber) {
        if (orderNumber < 0) {
            return DeliveryStatus.createErrorStatus(DeliveryStatus.DELIVERY_STATUS.NULL_VALUES, null, orderNumber);
        }
        Order order;
        try {
            order = clientOrder.retrieveOrder(orderNumber); // (1) consulta o pedido pelo número
        } catch (Exception e) {
            System.out.println("Order " + orderNumber + " not found.");
            return DeliveryStatus.createErrorStatus(DeliveryStatus.DELIVERY_STATUS.ORDER_NOT_FOUND, null, orderNumber);
        }

        if (order.getStatus() != Order.STATUS.CONFIRMED.ordinal()
                || order.getDeliveryStatus() != Order.DELIVERY_STATUS.PENDING.ordinal()) {
            System.out.println("Invalid order status: " + orderNumber + ": " + order.getStatus());
            return DeliveryStatus.createErrorStatus(DeliveryStatus.DELIVERY_STATUS.WRONG_ORDER_STATUS, null,
                    orderNumber);
        }

        order.setDeliveryStatus(Order.DELIVERY_STATUS.ON_ROUTE.ordinal());

        try {
            clientOrder.updateOrder(order); // (2) atualiza o status de entrega do pedido
        } catch (Exception e) {
            System.out.println("Erro no serviço de pedido: update");
            return DeliveryStatus.createErrorStatus(DeliveryStatus.DELIVERY_STATUS.ORDER_ERROR, null, orderNumber);
        }

        System.out.println("Sucesso ao inicializar a entrega: orderNumber: " + orderNumber);
        return new DeliveryStatus(DeliveryStatus.DELIVERY_STATUS.OK.toString(), null, orderNumber); // (3) retorna
                                                                                                    // sucesso
    }

    /**
     * Lógica de confirmação de entrega
     * (1) consulta o pedido pelo número
     * (2) verifica se o cliente foi encontrado
     * (3) confirma a entrega
     * (4) atualiza o status de entrega do pedido
     * (5) envia email para o cliente
     * (6) retorna sucesso
     *
     * @param deliveryCpf
     * @param orderNumber
     * @return
     */
    public DeliveryStatus confirmDeliveryOfOrder(int orderNumber, String deliveryCpf) {
        if (orderNumber < 0) {
            return DeliveryStatus.createErrorStatus(DeliveryStatus.DELIVERY_STATUS.NULL_VALUES, deliveryCpf,
                    orderNumber);
        }
        Order order;
        try {
            order = clientOrder.retrieveOrder(orderNumber); // (1) consulta o pedido pelo número
        } catch (Exception e) {
            System.out.println("Order " + orderNumber + " not found.");
            return DeliveryStatus.createErrorStatus(DeliveryStatus.DELIVERY_STATUS.ORDER_NOT_FOUND, deliveryCpf,
                    orderNumber);
        }

        // (2) verifica se o cliente foi encontrado
        if (deliveryCpf == null) {
            order.setDeliveryStatus(Order.DELIVERY_STATUS.PENDING.ordinal());
            try {
                clientOrder.updateOrder(order);
            } catch (Exception e) {
                System.out.println("Erro no serviço de pedido: update");
                return DeliveryStatus.createErrorStatus(DeliveryStatus.DELIVERY_STATUS.ORDER_ERROR, deliveryCpf,
                        orderNumber);
            }

            System.out.println("Client not found, returning status to " + order.getStatus());
            return DeliveryStatus.createErrorStatus(DeliveryStatus.DELIVERY_STATUS.CLIENT_NOT_FOUND, deliveryCpf,
                    orderNumber);
        }

        if (order.getStatus() != Order.STATUS.CONFIRMED.ordinal()
                || order.getDeliveryStatus() != Order.DELIVERY_STATUS.ON_ROUTE.ordinal()) {
            System.out.println("Invalid order status: " + orderNumber + ": " + order.getStatus());
            return DeliveryStatus.createErrorStatus(DeliveryStatus.DELIVERY_STATUS.WRONG_ORDER_STATUS, deliveryCpf,
                    orderNumber);
        }

        order.setDeliveryDate(new Date());
        order.setDeliveryCpf(deliveryCpf);
        order.setDeliveryStatus(Order.DELIVERY_STATUS.DELIVERED.ordinal()); // (3) confirma a entrega

        try {
            clientOrder.updateOrder(order); // (4) atualiza o status de entrega do pedido
        } catch (Exception e) {
            System.out.println("Erro no serviço de pedido: update");
            return DeliveryStatus.createErrorStatus(DeliveryStatus.DELIVERY_STATUS.ORDER_ERROR, deliveryCpf,
                    orderNumber);
        }

        try {
            clientEmail.callSendMailService(null, "Seu pedido chegou!",
                    "Acabamos de entregar seu pedido de numero " + orderNumber); // (5) envia email para o cliente
        } catch (Exception e) {
            System.out.println("Erro no serviço de email");
            return DeliveryStatus.createErrorStatus(DeliveryStatus.DELIVERY_STATUS.EMAIL_ERROR, deliveryCpf,
                    orderNumber);
        }

        System.out.println(
                "Sucesso ao confirmar a entrega: orderNumber: " + orderNumber + " deliveryCpf: " + deliveryCpf);
        return new DeliveryStatus(DeliveryStatus.DELIVERY_STATUS.OK.toString(), deliveryCpf, orderNumber); // (6)
                                                                                                           // retorna
                                                                                                           // sucesso
    }

    /**
     * Lista todos os pedidos para entrega
     *
     * @return
     */
    public List<Order> getOrdersToDelivery() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("status", String.valueOf(STATUS.CONFIRMED.ordinal()));
        queryParams.add("deliveryStatus", String.valueOf(Order.DELIVERY_STATUS.PENDING.ordinal()));

        return clientOrder.getOrders(queryParams);
    }
}
