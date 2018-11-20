package be.ucll.da.dentravak;
import be.ucll.da.dentravak.model.Order;
import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repositories.OrderRepository;
import be.ucll.da.dentravak.repositories.SandwichRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public CommandLineRunner demo(SandwichRepository sandwichRepository, OrderRepository orderRepository) {
        return (args) -> {
        };
    }
}