package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.csv.CsvView;
import be.ucll.da.dentravak.model.Order;
import be.ucll.da.dentravak.repositories.OrderRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
public class OrderController {
    private OrderRepository repository;
    public OrderController(OrderRepository repository){ this.repository = repository; }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
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

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public CsvView download(Model model) {
        model.addAttribute("orders", repository.findAll());
        repository.findAll().forEach(order -> order.printed());
        return new CsvView();
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
