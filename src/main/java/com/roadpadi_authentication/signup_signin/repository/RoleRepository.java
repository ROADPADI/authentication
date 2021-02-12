package com.roadpadi_authentication.signup_signin.repository;

import com.roadpadi_authentication.signup_signin.model.Role;
import com.roadpadi_authentication.signup_signin.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleEnum roleName);

}
