package edu.eci.cvds.Tasks.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String idUser;
    private String userName;
    private String passwd;
    private List<String> roles = new ArrayList<>();

    /**
     * Constructor de la clase User.
     * @param userName Nombre de usuario que identificará al usuario en el sistema.
     * @param passwd   Contraseña del usuario.
     */
    public User(String userName, String passwd) {
        this.userName = userName;
        this.passwd = passwd;
    }

    /**
     * Obtiene la lista de roles asociados al usuario.
     * @return Una lista de strings que representan los roles del usuario.
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Establece la lista de roles del usuario.
     * @param roles Una lista de cadenas con los roles que se asignarán al usuario.
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * Obtiene las autoridades concedidas al usuario, basadas en sus roles.
     * @return Una colección de GrantedAuthority, donde cada elemento representa un rol como autoridad.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña del usuario.
     */
    @Override
    public String getPassword() {
        String str = getPasswd();
        return str;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña del usuario.
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password La nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.passwd = password;
    }

    /**
     * Obtiene el nombre de usuario.
     * @return El nombre de usuario.
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * Obtiene el ID del usuario.
     * @return El ID del usuario.
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * Establece el ID del usuario.
     * @param idUser El nuevo ID del usuario.
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    // Métodos necesarios para implementar UserDetails
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
