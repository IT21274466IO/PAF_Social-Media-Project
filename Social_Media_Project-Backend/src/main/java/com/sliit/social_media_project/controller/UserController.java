package com.sliit.social_media_project.controller;

import com.sliit.social_media_project.UserRepository.UserRepository;
import com.sliit.social_media_project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUser() {

        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {

        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            return user.get();
        }

        throw new Exception("User not exist with userid" + id);
    }


    //Method for creating a new user
    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        return userRepository.save(newUser);
    }

    @PutMapping("/updateUser/{userid}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userid) throws Exception {

        Optional<User> user1 = userRepository.findById(userid);

        if(user1.isEmpty()) {
            throw new Exception("User not exists with id: " + userid);
        }

        User oldUser = user1.get();

        if(user.getFirstName() != null){
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null){
            oldUser.setLastName(user.getLastName());
        }
        if (user.getEmail() != null){
            oldUser.setEmail(user.getEmail());
        }

        return userRepository.save(oldUser);
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
}
