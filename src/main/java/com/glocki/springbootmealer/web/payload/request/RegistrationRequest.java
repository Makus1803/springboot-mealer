package com.glocki.springbootmealer.web.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class RegistrationRequest {
    @NotNull
    @Size(min = 3, max = 50, message = "Username must be between 2 and 50 characters")
    private String username;

    @NotNull(message = "Email address must be valid")
    @Size(min = 6, max=40, message = "Password must be between 6 and 40 characters")
    private String password;

    @Size(max = 30)
    private String name;

    @Email(message = "Email address must be valid")
    @Size(max = 100, message = "EmailAddress must be less than 100 characters")
    @NotNull
    private String email;

    private Set<String> roles;

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
