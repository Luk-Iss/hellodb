package com.example.hellodb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloDbService oracleService;

    public HelloController(HelloDbService oracleService) {
        this.oracleService = oracleService;
    }

    @GetMapping("/hello")
    public ResponseEntity<HelloResponse> getHelloMessage(@RequestParam(value = "name", defaultValue = "Světe") String name) {
        // Zavoláme Oracle funkci
        String messageFromDb = oracleService.callHelloFunction(name);

        // Vytvoříme DTO a vrátíme JSON odpověď
        HelloResponse response = new HelloResponse(messageFromDb);
        return ResponseEntity.ok(response);
    }
}