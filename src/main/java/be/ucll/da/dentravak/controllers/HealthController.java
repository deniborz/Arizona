package be.ucll.da.dentravak.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HealthController {

    @RequestMapping("/health-check")
    public ResponseEntity<String> myCustomCheck() {
        String message = "Testing my health check function";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
