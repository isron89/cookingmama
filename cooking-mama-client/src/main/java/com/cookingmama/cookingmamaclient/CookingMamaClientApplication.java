package com.cookingmama.cookingmamaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
@Controller
public class CookingMamaClientApplication {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }
    public static void main(String[] args) {
        SpringApplication.run(CookingMamaClientApplication.class, args);
//        Header contentTypeHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//        Header authorizationHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer 123token");
//
//        List<Header> headers = Arrays.asList(contentTypeHeader, authorizationHeader);
//
//        // set custom headers as default headers
//        HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();
//
//        HttpUriRequest request = RequestBuilder.get().setUri("url").build();
//        client.execute(request);
    }
}
