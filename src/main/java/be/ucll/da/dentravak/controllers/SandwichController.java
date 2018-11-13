package be.ucll.da.dentravak.controllers;

import java.math.BigDecimal;
import java.util.List;
import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repositories.SandwichRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SandwichController {
    private SandwichRepository repository;
    public SandwichController(SandwichRepository repository){
        this.repository = repository;
    }
    @RequestMapping("/test")
    public Sandwich test() {
        return new Sandwich.SandwichBuilder()
                .setIngredient("kaas test!")
                .setName("broodje kaas!!!")
                .setPrice(new BigDecimal(3.5)).build();
    }
    @RequestMapping("/sandwiches")
    public List<Sandwich> getSandwiches(){
        return repository.findAll();
    }
}