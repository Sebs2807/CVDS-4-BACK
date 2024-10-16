package edu.eci.cvds.Tasks.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tokens")
public class Token {
    @Id
    private String idToken;
    private String idUser;

    public String getIdUser() {
        return idUser;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
