package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Order;
import be.ucll.da.dentravak.repositories.OrderRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class OrderController {
    private OrderRepository repository;
    public OrderController(OrderRepository repository){ this.repository = repository; }

    @RequestMapping("/orders")
    public List<Order> getOrders(){
        return repository.findAll();
    }
}
