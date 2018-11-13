package be.ucll.da.dentravak.controllers;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;
import be.ucll.da.dentravak.model.Sandwich;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SandwichController {
    @RequestMapping("/test")
    public Sandwich test() {
        return new Sandwich.SandwichBuilder()
                .setIngredient("kaas")
                .setName("broodje kaas")
                .setPrice(new BigDecimal(3.5)).build();
    }
}