package edu.eci.cvds.Tasks.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Carga un usuario mediante su nombre de usuario
     * Este metodo trae un usuario del repositorio mediante el nombre de usuario dado
     * @param username nombre del usuario a cargar
     * @return Un onjeto UserDetails que contiene la información del usuario junto con sus autoridades
     * @throws UsernameNotFoundException si el usuario no se encuentra
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.trim()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities); 
    }

    /**
     * Guarda un nuevo usuario en el repositorio, codifica la contraseña del usuario antes de guardarlo en el repositorio
     * @param user El objeto usuario que contiene la información del usuario que se guardó en el repositorio
     */
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); 
        userRepository.save(user);
    }
}
