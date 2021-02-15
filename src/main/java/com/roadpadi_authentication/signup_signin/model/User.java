package com.roadpadi_authentication.signup_signin.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @NotBlank
//    @Size(max = 20)
    @Column(name = "users_username")
    private String username;

    @NotBlank
//    @Size(max = 100)
    @Email
    @Column(name = "users_email")
    private String email;

    @NotBlank
//    @Size(max = 200)
    @Column(name = "users_password")
    private String usersPassword;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "users_has_roles",
            joinColumns = @JoinColumn(name = "users_users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_roles_id"))
    private Set<Role> roles = new HashSet<>();

    public User(){}

    public User(String username, String email, String usersPassword){
        this.username = username;
        this.email = email;
        this.usersPassword = usersPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsersPassword() {
        return usersPassword;
    }

    public void setUserPassword(String usersPassword) {
        this.usersPassword = usersPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
