package com.example.basic_webclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "jsonPlaceholderWebClient")
    public WebClient userServiceWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://67c35ac91851890165aedae8.mockapi.io")
                .build();
    }


//    @Bean(name = "listOfJsonPlaceholderWebClient")
//    public WebClient listOfUserServiceWebClient(WebClient.Builder builder) {
//        return builder
//                .baseUrl("https://67c35ac91851890165aedae8.mockapi.io")
//                .build();
//    }


}
