package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Order;
import be.ucll.da.dentravak.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/orders")
    public Order postOrders(@RequestBody Order order){
        repository.save(order);
        return order;
    }

    @RequestMapping("/orders")
    public List<Order> getOrdersByDate(@RequestParam(value = "date", required = false) String date) {
        if (date == null){
            return repository.findAll();
        }
        return this.findByDate(date);
    }

    public List<Order> findByDate(String date) {
        List<Order> orders = repository.findAll();
        List<Order> filteredOrders = new ArrayList<>();
        LocalDate filterDate = LocalDate.parse(date);

        for (Order order : orders) {
            if (order.getCreationDate().toLocalDate().toString().equals(filterDate.toString())) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
}
