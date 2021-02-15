package com.roadpadi_authentication.signup_signin.model;

import javax.persistence.*;

@Entity
@Table(name = "roles", schema = "authentication_schema", catalog = "")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
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

    public void setRoleName(RoleEnum roleName){
        this.roleName = roleName;
    }

}
