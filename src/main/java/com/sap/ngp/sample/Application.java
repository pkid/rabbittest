package com.sap.ngp.sample;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;


@SpringBootApplication

public class Application {

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String auth = "guest:guest";
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        headers.set( "Authorization", authHeader );

        String uri = "http://localhost:15672/api/exchanges/%2f/my-new-exchange-new";

        String input = "{\"type\":\"direct\",\"durable\":\"true\"}";

        HttpEntity<String> entity = new HttpEntity<String>(input,headers);
        System.out.println(entity);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class);

        System.out.println(response);

    }
}
