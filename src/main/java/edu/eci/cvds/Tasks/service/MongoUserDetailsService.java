package edu.eci.cvds.Tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.eci.cvds.Tasks.model.User;
import edu.eci.cvds.Tasks.repository.UserRepository;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;  // Repositorio que maneja los usuarios en MongoDB

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inyectamos el PasswordEncoder

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscamos el usuario en la base de datos por su username
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Convertimos el usuario encontrado en un objeto UserDetails
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),  // La contraseña ya está cifrada en la base de datos
                user.getAuthorities());
    }

    public void saveUser(User user) {
        // Ciframos la contraseña antes de guardar el usuario
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Guardamos el usuario en la base de datos
        userRepository.save(user);
    }
    
}

