package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.model.SandwichPreferences;
import be.ucll.da.dentravak.repositories.SandwichRepository;
import javassist.NotFoundException;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.naming.ServiceUnavailableException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
public class SandwichController {

    @Inject
    private DiscoveryClient discoveryClient;

    @Inject
    private SandwichRepository repository;

    @Inject
    private RestTemplate restTemplate;

    /*@RequestMapping("/test")
    public Sandwich test() {
        return new Sandwich.SandwichBuilder()
                .setIngredients("kaas test!")
                .setName("broodje kaas!!!")
                .setPrice(new BigDecimal(3.5)).build();
    }*/

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
    public List<Sandwich> sandwiches() {
        try {
            SandwichPreferences preferences = getPreferences("ronald.dehuysser@ucll.be");
            //TODO: sort allSandwiches by float in preferences
            List<Sandwich> sandwiches = repository.findAll();
            return sandwiches;
        } catch (ServiceUnavailableException e) {
            return repository.findAll();
        }
    }


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

    @RequestMapping("/getpreferences/{emailAddress}")
    public SandwichPreferences getPreferences(@PathVariable String emailAddress) throws RestClientException, ServiceUnavailableException {
        URI service = recommendationServiceUrl()
                .map(s -> s.resolve("/recommendation/recommend/" + emailAddress))
                .orElseThrow(ServiceUnavailableException::new);
        return restTemplate
                .getForEntity(service, SandwichPreferences.class)
                .getBody();
    }

    public Optional<URI> recommendationServiceUrl() {
        return discoveryClient.getInstances("recommendation")
                .stream()
                .map(si -> si.getUri())
                .findFirst();
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