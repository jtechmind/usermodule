package com.jtech.usermodule.controller;

import com.jtech.usermodule.dto.UserRequestDTO;
import com.jtech.usermodule.dto.UserResponseDTO;
import com.jtech.usermodule.model.User;
import com.jtech.usermodule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public String welcome(){
        return "Welcome to the user service";
    }

    // Create New User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO userRequest){
        return ResponseEntity.ok(service.createUser(userRequest));
    }

    // Get User By UserName
    @GetMapping("/username/{userName}")
    public ResponseEntity<User> getUserByName(@PathVariable String userName){
        return service.findUserByUserName(userName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get User By Email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        return service.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get User By Id
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get All Users
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Update an existing user
    @PutMapping("/update/{userName}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String userName, @RequestBody UserRequestDTO userRequest){
        return service.updateUser(userName,userRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an existing user
    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName){
        service.deleteUser(userName);
        return ResponseEntity.notFound().build();
    }



    
}
