package com.colak.springtutorial.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CorsIntegrationTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    // This test does not work
    @Test
    void testCorsHeaders() {
        // Set preflight headers
        String origin = "http://127.0.0.1:8080";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ORIGIN, origin);
        headers.add(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");

        // Build the preflight OPTIONS request
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        // Make the request to a valid endpoint (replace `/some-endpoint`)
        String url = "http://localhost:" + port + "/hello";
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.OPTIONS,
                requestEntity,
                String.class);

        // Validate CORS headers in the response
        HttpHeaders responseHeaders = response.getHeaders();
        assertThat(responseHeaders.getAccessControlAllowOrigin()).isEqualTo(origin);
        assertThat(responseHeaders.getAccessControlAllowMethods()).contains(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE);
        assertThat(responseHeaders.getAccessControlAllowCredentials()).isTrue();
    }
}

