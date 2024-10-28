package edu.eci.cvds.Tasks.controller;

import edu.eci.cvds.Tasks.model.Token;
import edu.eci.cvds.Tasks.model.User;
import edu.eci.cvds.Tasks.service.AuthService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/{userName}/{password}")
    public Token authentication(@PathVariable String userName, @PathVariable String password) {
        User user = new User(userName, password);
        return authService.logIn(user);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return authService.createUser(user);
    }

    @DeleteMapping
    public void deleteSession(@RequestBody Token token) {
        authService.logOut(token);
    }

    @PostMapping("/assignRole")
    public User assignRoleToUser(@RequestParam String userId, @RequestParam String role) {
        return authService.assignRoleToUser(userId, role);
    }
    

    @GetMapping("/roles/{userName}")
    public List<String> getUserRoles(@PathVariable String userName) {
        return authService.getUserRolesByUserName(userName);
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "El endpoint está funcionando.";
    }
}
