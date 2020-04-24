package iot.lviv.com.controller;

import iot.lviv.com.domain.User;
import iot.lviv.com.exeption.ResourceNotFoundException;
import iot.lviv.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public User updateNote(@PathVariable(value = "id") Integer userId,
                              @Valid @RequestBody User userContent) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setLogin(userContent.getLogin());
        user.setEmail(userContent.getEmail());
        user.setPassword(userContent.getPassword());

        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserByiD(@PathVariable(value = "id") Integer userId) {
        return userRepository.findById(userId);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
}
