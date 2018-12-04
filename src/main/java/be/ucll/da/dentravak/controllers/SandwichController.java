package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repositories.SandwichRepository;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SandwichController {
    private SandwichRepository repository;
    public SandwichController(SandwichRepository repository){
        this.repository = repository;
    }

    @RequestMapping("/test")
    public Sandwich test() {
        return new Sandwich.SandwichBuilder()
                .setIngredients("kaas test!")
                .setName("broodje kaas!!!")
                .setPrice(new BigDecimal(3.5)).build();
    }

    @RequestMapping(value = "/sandwiches/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") UUID id) {
        System.out.println("Fetching sandwich with id " + id);
        Optional<Sandwich> sandwich = repository.findById(id);
        if (sandwich == null) {
            System.out.println("Sandwich with id " + id + " not found.");
            return new ResponseEntity(new NotFoundException("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sandwich.get(), HttpStatus.OK);
    }

    @RequestMapping("/sandwiches")
    public List<Sandwich> getSandwiches(){ return repository.findAll(); }

    @PutMapping("/sandwiches")
    public Sandwich updateSandwich(@RequestBody Sandwich sandwich){
        return repository.save(sandwich);
    }

    @PostMapping("/sandwiches")
    public Sandwich saveSandwich(@RequestBody Sandwich sandwich){
        return repository.save(new Sandwich.SandwichBuilder()
                .setName(sandwich.getName())
                .setPrice(sandwich.getPrice())
                .setIngredients(sandwich.getIngredients())
                .build());
    }

//    @RequestMapping("/sandwiches/cart")
//    public List<Sandwich> saveSandwichToCart(@CookieValue(value = "sandwiches", defaultValue = "niks") String sandwiches){
//        Gson googleJson = new Gson();
//        ArrayList sandwichList = googleJson.fromJson(sandwiches, ArrayList.class);
//        return sandwichList;
//    }
//
//    @PostMapping("/sandwiches/cart")
//    public void saveSandwichToCart(@RequestBody Sandwich sandwich,
//                             @CookieValue(value = "sandwiches", defaultValue = "niks") String sandwiches,
//                             HttpServletResponse response){
//
//        Sandwich savedSandwich = new Sandwich.SandwichBuilder()
//                .setName(sandwich.getName())
//                .setPrice(sandwich.getPrice())
//                .setIngredients(sandwich.getIngredients())
//                .build();
//
//        ArrayList<Sandwich> savedSandwiches = new ArrayList<>();
//        if(sandwiches != null || !sandwiches.isEmpty()) {
//            Gson googleJson = new Gson();
//            savedSandwiches = googleJson.fromJson(sandwiches, ArrayList.class);
//            System.out.println(sandwiches);
//        }
//        savedSandwiches.add(savedSandwich);
//        System.out.println(savedSandwiches);
//        response.addCookie(new Cookie("sandwiches", savedSandwiches.toString()));
//    }
}