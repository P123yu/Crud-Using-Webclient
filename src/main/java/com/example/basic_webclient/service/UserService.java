package com.example.basic_webclient.service;

import com.example.basic_webclient.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    User getUserById(String userId);


    List<User> getUsers();

    User createUser(User user);

    List<User> createListOfUser(List<User>users);

    void deleteUserById(Long userId);

    User updateUser(Long id, User updatedUser);




}
