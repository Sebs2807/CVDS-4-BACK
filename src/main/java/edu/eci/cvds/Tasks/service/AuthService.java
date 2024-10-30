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

    /**
     * Método para autenticar el usuario en el sistema y generar un token de sesión si la contraseña es correcta
     * @param user Usuario que se registró que contiene las credenciales proporcionadas por el usuario
     * @return Token si la autenticación es exitosa, o null si las el usuario no existe
     */
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

    /**
     * Método para crear un usuario asignandole como rol predeterminado el rol de usuario y asignandole la contraseña encriptada 
     * @param user Usuario que se registró
     * @return Usuario con la contraseña nueva y encriptada y la lista de roles como rol predeterminado el de usuario
     */
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

    /**
     * Método para hacer el logout de la aplicación
     * @param token Token del usuario que quiere salir de la aplicación para eliminarlo
     */
    public void logOut(Token token) {
        tokenRepository.deleteById(token.getIdToken());
    }

    /**
     * Método para asignar un rol a un usuario ya existente
     * @param userId ID del usuario que se le quiere añadir un rol
     * @param role Rol que se le quiere agregar al usuario
     * @return Usuario con el nuevo rol ya asignado
     */
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

    /**
     * Método para obtener los roles de un usuario mediante el nombre de usuario
     * @param userName Nombre del usuario del que se quieren obtener los roles
     * @return Lista de strings que contiene los roles del usuario
     */
    public List<String> getUserRolesByUserName(String userName) {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getRoles();
        }
        return Collections.emptyList();
    }
}
