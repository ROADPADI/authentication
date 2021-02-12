package com.roadpadi_authentication.signup_signin.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private RoleEnum roleName;

    public Role(){}

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public RoleEnum getRoleName(){
        return roleName;
    }

    public void setRoleName(RoleEnum roleName){
        this.roleName = roleName;
    }


}
