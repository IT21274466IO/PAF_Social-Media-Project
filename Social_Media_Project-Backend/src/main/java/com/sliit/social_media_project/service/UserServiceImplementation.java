package com.sliit.social_media_project.service;

import com.sliit.social_media_project.models.User;
import com.sliit.social_media_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setGender(user.getGender());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            return user.get();
        }

        throw new Exception("User not exist with userid" + userId);
    }

    @Override
    public User findUserByEmail(String email) {

        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {

        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }

        return userRepository.findById(userId)
                .map(oldUser -> {
                    if (user.getFirstName() != null) {
                        oldUser.setFirstName(user.getFirstName());
                    }
                    if (user.getLastName() != null) {
                        oldUser.setLastName(user.getLastName());
                    }
                    if (user.getEmail() != null) {
                        oldUser.setEmail(user.getEmail());
                    }
                    if (user.getGender() != null) {
                        oldUser.setGender(user.getGender());
                    }
                    return userRepository.save(oldUser);
                })
                .orElseThrow(() -> new Exception("User not exists with id: " + userId));
    }


    @Override
    public List<User> searchUser(String query) {
        List<User> users = userRepository.searchUserBy(query);
        return users;
    }
}
