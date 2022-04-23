package br.inatel.dm112.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.dm112.interfaces.Logistics;
import br.inatel.dm112.model.DeliveryStatus;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.service.LogisticsService;

@RestController
@RequestMapping("/api")
public class LogisticsRest implements Logistics {

    @Autowired
    private LogisticsService service;

    @Override
    @PostMapping("/delivery/start")
    @ResponseStatus(HttpStatus.OK)
    public DeliveryStatus startDeliveryOfOrder(@RequestBody Order order) {
        return service.startDeliveryOfOrder(order.getNumber());
    }

    @Override
    @PostMapping("/delivery/confirm")
    @ResponseStatus(HttpStatus.OK)
    public DeliveryStatus confirmDeliveryOfOrder(@RequestBody Order order) {
        return service.confirmDeliveryOfOrder(order.getNumber(), order.getDeliveryCpf());
    }

    @Override
    @GetMapping("/delivery/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> listOrdersToDelivery() {
        return service.getOrdersToDelivery();
    }

}
