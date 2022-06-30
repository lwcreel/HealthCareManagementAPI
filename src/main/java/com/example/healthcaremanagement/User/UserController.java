package com.example.healthcaremanagement.User;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserRepository repository;

    @PostMapping("/register")
    User registerUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @PutMapping("/user")
    User updateUserByUserId(@RequestBody User newUser, @RequestParam ("id") Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setPhoneNumber(newUser.getPhoneNumber());
                    user.setFunds(newUser.getFunds());
                    user.setAdmin(newUser.isAdmin());

                    return repository.save(user);
                }).orElseGet(() -> {
                    return repository.save(newUser);
                });
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    List<User> getAllUsers(Principal principal) {
        return repository.findAll();
    }

    @GetMapping("/user")
    User getUserByUserId(@RequestParam ("id") Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping(value = "/login")
    String loginUser (@RequestParam("id") Long id,
                 @RequestParam("password") String password) {

        List<User> users = repository.findAll();
        for (User u : users) {
            if (u.getId() == id && u.getPassword().equals(password) && !u.isAdmin())

                return "Login Successful, welcome " + u.getFirstName() + " " + u.getLastName();
        }
        throw new UserNotFoundException(id);
    }

    @DeleteMapping("/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    void deleteUserByUserId (@RequestParam ("id") Long id) {

        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException(id);

        repository.deleteById(id);
    }
}
