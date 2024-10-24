package edu.eci.cvds.Tasks.service;

import edu.eci.cvds.Tasks.model.Token;
import edu.eci.cvds.Tasks.model.User;
import edu.eci.cvds.Tasks.repository.TokenRepository;
import edu.eci.cvds.Tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public Token logIn(User user){
        Optional<User> optionalUser = userRepository.findByUserName(user.getUsername());
        
        if (optionalUser.isPresent()){
            User userDB = optionalUser.get();
            if(userDB.getPasswd().equals(user.getPasswd())){
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

    public User createUser(User user){
        Optional<User> optionalUser = userRepository.findByUserName(user.getUsername());

        if (optionalUser.isEmpty()){
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public void logOut(Token token){
        tokenRepository.deleteById(token.getIdToken());
    }


}
