package com.finwefeanor.DiningReviewApi.controller;
import com.finwefeanor.DiningReviewApi.model.User;
import com.finwefeanor.DiningReviewApi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/byDisplayName")
    public User getUserByDisplayName(@RequestParam String displayName) {

        Optional<User> userOptionalToGet = userRepository.findByDisplayName(displayName);

        if(userOptionalToGet.isPresent()){
            return userOptionalToGet.get();
        }
        else {
                throw new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "User not found");
        }
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                             @RequestBody User user) {
        // Using Java's Optional, find the user by ID
        Optional<User> userOptionalToUpdate = userRepository.findById(id);
        if (!userOptionalToUpdate.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        User existingUser = userOptionalToUpdate.get();
        // Update the existing user's properties with the new data (can't update display name since its unique)
        existingUser.setCity(user.getCity());
        existingUser.setCountry(user.getCountry());
        existingUser.setZipCode(user.getZipCode());
        // Save the updated user
        return userRepository.save(existingUser);
    }
    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        //creates a user profile for an unregistered user, using a display name that’s unique only to user.
        Optional<User> existingUser = userRepository.findByDisplayName(user.getDisplayName());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User already exist");
        }
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
