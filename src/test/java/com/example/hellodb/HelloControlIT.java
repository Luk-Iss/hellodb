package com.example.hellodb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate; // DŮLEŽITÉ: Nový import
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus; // Pro kontrolu HTTP statusu
import org.springframework.http.ResponseEntity; // Pro práci s odpovědí
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @TestPropertySource(locations = "classpath:application.properties") // Otestuj bez této anotace, jak jsme si říkali dříve
class HelloControlIT {

    // Tato anotace zajistí, že Spring Boot spustí aplikaci na náhodném volném portu
    @LocalServerPort
    private int port;

    // TestRestTemplate pro provádění HTTP požadavků
    @Autowired
    private TestRestTemplate restTemplate; // DŮLEŽITÉ: Autowire TestRestTemplate místo WebTestClient

    @Test
    void testHelloEndpoint() {
        String name = "Gemini";
        String expectedMessage = "Ahoj, " + name + "! Zdraví tě Oracle databáze.";

        // Používáme getForEntity, abychom získali ResponseEntity, která obsahuje status a tělo
        ResponseEntity<HelloResponse> responseEntity = restTemplate.getForEntity(
            "http://localhost:" + port + "/hello?name=" + name,
            HelloResponse.class
        );

        // Ověření statusu
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Ověření těla odpovědi
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getMessage()).isEqualTo(expectedMessage);
    }

    // Další testy pro různé scénáře, např. s výchozí hodnotou
    @Test
    void testHelloEndpointWithDefaultName() {
        String expectedMessage = "Ahoj, Světe! Zdraví tě Oracle databáze.";

        ResponseEntity<HelloResponse> responseEntity = restTemplate.getForEntity(
            "http://localhost:" + port + "/hello", // Bez parametru name
            HelloResponse.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getMessage()).isEqualTo(expectedMessage);
    }
}