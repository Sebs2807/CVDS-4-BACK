package edu.eci.cvds.Tasks.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import edu.eci.cvds.Tasks.repository.UserRepository;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<edu.eci.cvds.Tasks.model.User> user = userRepository.findByuserName(username);
        if (!(user.isPresent())) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(user.get().getUserName())
                .password(user.get().getPasswd())
                .roles(user.get().getRoles().toArray(new String[0]))
                .build();
    }
}

