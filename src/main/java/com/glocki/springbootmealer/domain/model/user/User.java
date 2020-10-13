package com.glocki.springbootmealer.domain.model.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    String id;

    @NotBlank
    @Size(max = 15)
    private String username;

    @NotBlank
    @Size(max=30)
    private String password;

    @Size(max = 30)
    private String name;

    @Size(max = 50)
    private String email;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public static User create(@NotBlank @Size(max = 15) String username, @NotBlank @Size(max = 30) String password, String name, String email) {
        User user = new User();
        user.username = username;
        user.password = password;
        user.name = name;
        user.email = email;
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
