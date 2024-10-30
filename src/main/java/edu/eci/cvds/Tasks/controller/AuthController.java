package edu.eci.cvds.Tasks.controller;

import edu.eci.cvds.Tasks.model.Token;
import edu.eci.cvds.Tasks.model.User;
import edu.eci.cvds.Tasks.service.AuthService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    /**
     * Autentica a un usuario basado en su nombre de usuario y contraseña.
     * @param userName el nombre de usuario del usuario que intenta iniciar sesión.
     * @param password la contraseña del usuario que intenta iniciar sesión.
     * @return un objeto Token que representa el token de autenticación del usuario.
     */
    @GetMapping("/{userName}/{password}")
    public Token authentication(@PathVariable String userName, @PathVariable String password) {
        User user = new User(userName, password);
        return authService.logIn(user);
    }

    /**
     * Crea un nuevo usuario en el sistema.
     * @param user un objeto User que contiene la información del nuevo usuario.
     * @return el objeto User creado en el sistema.
     */
    @PostMapping
    public User createUser(@RequestBody User user) {
        return authService.createUser(user);
    }

    /**
     * Elimina la sesión de un usuario basado en el token proporcionado.
     * @param token un objeto Token que representa la sesión que se desea eliminar.
     */
    @DeleteMapping
    public void deleteSession(@RequestBody Token token) {
        authService.logOut(token);
    }

    /**
     * Asigna un rol a un usuario específico.
     * @param userId el identificador del usuario al que se le asignará el rol.
     * @param role el rol que se asignará al usuario.
     * @return el objeto User actualizado con el nuevo rol.
     */
    @PostMapping("/assignRole")
    public User assignRoleToUser(@RequestParam String userId, @RequestParam String role) {
        return authService.assignRoleToUser(userId, role);
    }
    
    /**
     * Obtiene la lista de roles asociados a un usuario dado su nombre de usuario.
     * @param userName el nombre de usuario del cual se desea obtener los roles.
     * @return una lista de cadenas que representan los roles del usuario.
     */
    @GetMapping("/roles/{userName}")
    public List<String> getUserRoles(@PathVariable String userName) {
        return authService.getUserRolesByUserName(userName);
    }

    /**
     * Endpoint de prueba para verificar que el controlador esté funcionando.
     * @return un mensaje de confirmación.
     */
    @GetMapping("/test")
    public String testEndpoint() {
        return "El endpoint está funcionando.";
    }
}
