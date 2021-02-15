package com.roadpadi_authentication.signup_signin.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, name = "roles_name")
    private RoleEnum roleName;

    public Role(){}

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public RoleEnum getRoleName(){
        return roleName;
    }

    public boolean setRoleName(RoleEnum roleName){
        this.roleName = roleName;
        return false;
    }

}
