package com.example.basic_webclient.controller;

import com.example.basic_webclient.model.User;
import com.example.basic_webclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> fetchPostById(@PathVariable String id){
        User user=userService.getUserById(id);
        return user!=null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> fetchAll(){
        List<User> user=userService.getUsers();
        return !user.isEmpty() ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user){
        User createdUser=userService.createUser(user);
        return createdUser!=null ? ResponseEntity.ok(user) : ResponseEntity.badRequest().build();
    }


    @PostMapping("/createAll")
    public ResponseEntity<?> createListOfUser(@RequestBody List<User> users){
        List<User> createdUserList=userService.createListOfUser(users);
        return !createdUserList.isEmpty() ? ResponseEntity.ok(createdUserList) : ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> createListOfUser(@PathVariable Long id){
        try{
            userService.deleteUserById(id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        User user=userService.updateUser(id,updatedUser);
        return user!=null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }




}
