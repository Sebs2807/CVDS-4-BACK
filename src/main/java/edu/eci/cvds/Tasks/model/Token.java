package edu.eci.cvds.Tasks.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tokens")
public class Token {
    @Id
    private String idToken;
    private String idUser;
    private List<String> roles;

    /**
     * Obtiene la lista de roles asociados a este token.
     * @return lista de roles asignados al token.
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Asigna una lista de roles a este token.
     * @param roles lista de roles a ser asignados al token.
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * Obtiene el ID del usuario asociado a este token.
     * @return el ID del usuario.
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * Asigna el ID del usuario asociado a este token.
     *
     * @param idUser el ID del usuario.
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * Obtiene el ID del token.
     * @return el ID del token.
     */
    public String getIdToken() {
        return idToken;
    }

    /**
     * Asigna el ID del token.
     * @param idToken el ID del token.
     */
    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
