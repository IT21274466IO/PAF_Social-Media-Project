package com.sliit.social_media_project.controller;

import com.sliit.social_media_project.repository.UserRepository;
import com.sliit.social_media_project.models.User;
import com.sliit.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUser() {

        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {

        User user = userService.findUserById(id);
        return user;
    }


    //Method for creating a new user
    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {

        User savedUser = userService.registerUser(user);
        return savedUser;
    }

    @PutMapping("/updateUser/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {

        User updatedUser = userService.updateUser(user, userId);
        return updatedUser;
    }

    @DeleteMapping("deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) throws Exception {

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()){
            throw new Exception("User not exists with id: " + userId);
        }

        userRepository.delete(user.get());

        return "User deleted successfully with id: " + userId;
    }

    @PutMapping("/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        User user = userService.followUser(userId1, userId2);
        return user;
    }

    @GetMapping("/users/search")
    public ResponseEntity<?> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);
        // Check if the returned list is empty
        if (users.isEmpty()) {
            // Return an error response with appropriate status code and message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found with the given name or email: " + query);
        }
        return ResponseEntity.ok(users);
    }
}
