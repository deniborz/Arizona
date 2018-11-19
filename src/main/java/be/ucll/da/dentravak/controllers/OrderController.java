package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Order;
import be.ucll.da.dentravak.repositories.OrderRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {
    private OrderRepository repository;
    public OrderController(OrderRepository repository){ this.repository = repository; }

    /*
    @RequestMapping("/orders")
    public List<Order> getOrders(){
        return repository.findAll();
    }*/

    @PostMapping("/orders")
    public List<Order> postOrders(){ return repository.findAll(); }

    @RequestMapping("/orders")
    public List<Order> getOrdersByDate(@RequestParam(value = "date") String date) { return this.findByDate(date); }

    public List<Order> findByDate(String date) {
        List<Order> orders = repository.findAll();
        List<Order> filteredOrders = new ArrayList<>();
        LocalDate filterDate = LocalDate.parse(date);

        for (Order order : orders) {
            if (order.getDate().toLocalDate().toString().equals(filterDate.toString())) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
}
