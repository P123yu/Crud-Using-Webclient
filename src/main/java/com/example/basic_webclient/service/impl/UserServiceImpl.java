package com.example.basic_webclient.service.impl;

import com.example.basic_webclient.client.UserClient;
import com.example.basic_webclient.model.User;
import com.example.basic_webclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserClient userClient;


    @Override
    public User getUserById(String userId) {
        return userClient.getPost(userId);
    }

    @Override
    public List<User> getUsers() {
        return userClient.getPostList();
    }

    @Override
    public User createUser(User user) {
        return userClient.createUser(user);
    }

    @Override
    public List<User> createListOfUser(List<User> users) {
        return userClient.createListOfUser(users);
    }

    @Override
    public void deleteUserById(Long userId) {
        userClient.deleteUserById(userId);
    }

    @Override
    public User updateUser(Long userId, User updatedUser) {
        return userClient.updateUser(userId,updatedUser);
    }
}
