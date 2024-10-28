package edu.eci.cvds.Tasks.service;

import edu.eci.cvds.Tasks.model.Token;
import edu.eci.cvds.Tasks.model.User;
import edu.eci.cvds.Tasks.repository.TokenRepository;
import edu.eci.cvds.Tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Token logIn(User user) {
        Optional<User> optionalUser = userRepository.findByUserName(user.getUsername());

        if (optionalUser.isPresent()) {
            User userDB = optionalUser.get();
            if (passwordEncoder.matches(user.getPasswd(), userDB.getPasswd())) {
                Token token = new Token();
                token.setIdUser(userDB.getIdUser());
                token.setRoles(userDB.getRoles());
                return tokenRepository.save(token);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public User createUser(User user) {
        Optional<User> optionalUser = userRepository.findByUserName(user.getUsername());
        if (optionalUser.isEmpty()) {
            user.setRoles(Arrays.asList("ROLE_USER"));
            user.setPassword(passwordEncoder.encode(user.getPasswd()));
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public void logOut(Token token) {
        tokenRepository.deleteById(token.getIdToken());
    }

    public User assignRoleToUser(String userId, String role) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.getRoles().add(role.strip());
            userRepository.save(user);
            return user;
        } else {
            throw new NoSuchElementException("User not found with id: " + userId);
        }
    }

    public List<String> getUserRolesByUserName(String userName) {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getRoles();
        }
        return Collections.emptyList();
    }
}
