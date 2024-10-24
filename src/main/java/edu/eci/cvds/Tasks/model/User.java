package edu.eci.cvds.Tasks.model;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document(collection = "users")
public class User implements UserDetails{
    @Id
    private String idUser;
    private String userName;
    private String passwd;
    private Set<String> roles;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
    }
    
    @Override
    public String getPassword(){
        String str = getPasswd();
        return str;
    }

    public String getPasswd() {
        return passwd;
    }
    
    public void setPassword(String password) {
        this.passwd = password;
    }
    
    public String getUsername() {
        return userName;
    }

    public User(String userName, String passwd) {
        this.userName = userName;
        this.passwd = passwd;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

}
