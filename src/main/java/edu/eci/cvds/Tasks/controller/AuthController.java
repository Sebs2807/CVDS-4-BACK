package edu.eci.cvds.Tasks.controller;

import edu.eci.cvds.Tasks.model.Token;
import edu.eci.cvds.Tasks.model.User;
import edu.eci.cvds.Tasks.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/{userName}/{passwd}")
    public Token authentication(@PathVariable String userName, @PathVariable String passwd) {
        User user = new User(userName, passwd);
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

}
