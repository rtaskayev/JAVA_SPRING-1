package ru.netology.springboot_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBoot1ApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private final GenericContainer<?> myAppFirst = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    @Container
    private final GenericContainer<?> myAppSecond = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);



    @Test
    void contextLoads() {
        final String expectedDev = "Current profile is dev";
        final String expectedProd = "Current profile is production";

        ResponseEntity<String> entityFromFirst = restTemplate.getForEntity("http://localhost:" + myAppFirst.getMappedPort(8080) + "/profile", String.class);
        ResponseEntity<String> entityFromSecond = restTemplate.getForEntity("http://localhost:" + myAppSecond.getMappedPort(8081) + "/profile", String.class);

        final String resultDEV = entityFromFirst.getBody();
        final String resultProd = entityFromSecond.getBody();

        Assertions.assertEquals(expectedDev, resultDEV);
        Assertions.assertEquals(expectedProd, resultProd);
    }

}
