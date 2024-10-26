package edu.eci.cvds.Tasks.service;

import edu.eci.cvds.Tasks.model.Token;
import edu.eci.cvds.Tasks.model.User;
import edu.eci.cvds.Tasks.repository.TokenRepository;
import edu.eci.cvds.Tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public User addRoleToUser(String userId, String role) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<String> roles = user.getRoles();
        if (roles == null) {
            roles = new ArrayList<>();
        }
        if (!roles.contains(role)) {
            roles.add(role.trim());
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

}
