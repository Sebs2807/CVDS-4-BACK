package edu.eci.cvds.Tasks.service;

import edu.eci.cvds.Tasks.model.Token;
import edu.eci.cvds.Tasks.model.User;
import edu.eci.cvds.Tasks.repository.TokenRepository;
import edu.eci.cvds.Tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public Token logIn(String userName, String passwd){
        Optional<User> optionalUser = userRepository.findByUsername(userName);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.getPasswd().equals(passwd)){
                Token token = new Token();
                token.setIdUser(user.getIdUser());
                return tokenRepository.save(token);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public User createUser(String userName, String passwd){
        Optional<User> optionalUser = userRepository.findByUsername(userName);

        if (optionalUser.isEmpty()){
            User user = new User(userName, passwd);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public void logOut(Token token){
        tokenRepository.deleteById(token.getIdToken());
    }


}
