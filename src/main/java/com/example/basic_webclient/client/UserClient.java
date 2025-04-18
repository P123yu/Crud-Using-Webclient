package com.example.basic_webclient.client;

import com.example.basic_webclient.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class UserClient {

    @Autowired
    @Qualifier("jsonPlaceholderWebClient")
    private WebClient webClient;

    @Autowired
    private KafkaTemplate<Object, String> kafkaTemplate;


    @Autowired
    private ObjectMapper objectMapper;

    // ✅ Method to send message to Kafka topic
    public void sendToTopic1(User user) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(user);
        kafkaTemplate.send("firstTopic15", json);
    }


    public User getPost(String userId) throws JsonProcessingException {
        User user = webClient.get()
                .uri("/crud/" + userId)
                .retrieve()
                .bodyToMono(User.class)
                .block(); // Note: blocking in reactive is not ideal, but OK for simple use cases

        // ✅ Send user info (as string) to Kafka topic
        if (user != null) {
            sendToTopic1(user); // or convert to JSON using ObjectMapper
        }

        return user;
    }

    public List<User> getPostList() {
        return webClient.get()
                .uri("/crud")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block(); // For simplicity; in production, use reactive chains
    }


    public User createUser(User user) {
        return webClient.post()
                .uri("/crud")
                .bodyValue(user) // send the user object in the request body
                .retrieve()
                .bodyToMono(User.class)
                .block(); // synchronous; okay if you're not in a reactive flow
    }


    public List<User> createListOfUser(List<User> users) {
        return webClient.post()
                .uri("/crud") // use your correct endpoint
                .bodyValue(users)  // 👈 send list directly
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();
    }


    public void deleteUserById(Long userId) {
        webClient.delete()
                .uri("/crud/" + userId)
                .retrieve()
                .bodyToMono(Void.class)
                .block(); // Again, blocking is not ideal in reactive apps
    }


    public User updateUser(Long userId, User updatedUser) {
        return webClient.put()
                .uri("/crud/" + userId)
                .bodyValue(updatedUser) // Sends the updated user as the request body
                .retrieve()
                .bodyToMono(User.class)
                .block(); // Blocking for simplicity
    }

}
