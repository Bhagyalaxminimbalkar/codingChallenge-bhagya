package com.coding.bhagya.companyMerger.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductCatalogControllerTest {

    public static final String CSV_FILES = "barcodesA.csv,barcodesB.csv,catalogA.csv,catalogB.csv";

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testMergingProductCatalog() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/upload-csv-file/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        String files = CSV_FILES;

        HttpEntity<String> request = new HttpEntity<String>(files, headers);
        ResponseEntity<String> result = testRestTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    public void testAddProduct() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/addProduct/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        String input = "123-cov-456,Toilet Paper,catalogA.csv";

        HttpEntity<String> request = new HttpEntity<>(input, headers);
        ResponseEntity<String> result = testRestTemplate.postForEntity(uri, request, String.class);
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }


    @Test
    public void testRemoveProduct() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/deleteProduct/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        String input = "123-cov-456,Toilet Paper,catalogA.csv";

        HttpEntity<String> request = new HttpEntity<>(input, headers);
        ResponseEntity<String> result = testRestTemplate.postForEntity(uri, request, String.class);
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testUpdateProduct() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/updateProduct/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        String input = "00006,999-epd-782,b3219999949999,barcodesB.csv";

        HttpEntity<String> request = new HttpEntity<>(input, headers);
        ResponseEntity<String> result = testRestTemplate.postForEntity(uri, request, String.class);
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

}
