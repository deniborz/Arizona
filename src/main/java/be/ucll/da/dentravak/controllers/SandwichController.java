package be.ucll.da.dentravak.controllers;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repositories.SandwichRepository;
import javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.openmbean.KeyAlreadyExistsException;

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

    @PostMapping("/sandwiches")
    public ResponseEntity<?> updateSandwich(@RequestBody Sandwich sandwich){
        if (repository.existsById(sandwich.getId())) {
            System.out.println("Unable to create. A sandwich with name " + sandwich.getName() + " already exist");
            return new ResponseEntity(new KeyAlreadyExistsException("User with id " + sandwich.getId()
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        repository.save(sandwich);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(sandwich.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/sandwiches")
    public ResponseEntity<?> saveSandwich(@RequestBody Sandwich sandwich){
        if (repository.existsById(sandwich.getId())) {
            System.out.println("Unable to create. A sandwich with name " + sandwich.getName() + " already exist");
            return new ResponseEntity(new KeyAlreadyExistsException("User with id " + sandwich.getId()
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        repository.save(sandwich);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(sandwich.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}