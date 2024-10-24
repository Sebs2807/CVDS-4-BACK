package edu.eci.cvds.Tasks.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String idUser;

    private String userName;
    private String passwd;
    private Set<String> roles;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }


    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public User(String userName, String passwd) {
        this.userName = userName;
        this.passwd = passwd;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getIdUser() {
        return idUser;
    }


    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

}
