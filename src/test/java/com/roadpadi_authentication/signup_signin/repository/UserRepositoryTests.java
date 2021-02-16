//package com.roadpadi_authentication.signup_signin.repository;
//
//import com.roadpadi_authentication.signup_signin.model.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
//
//import javax.persistence.EntityManager;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
//public class UserRepositoryTests {
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void createUserTest(){
//        User user = new User();
//        user.setEmail("tega@gmail.com");
//        user.setUsername("tegaisiboge");
//        user.setUserPassword("4321tegz");
//        user.setRoles(user.getRoles());
//
//        User savedUser = userRepository.save(user);
//        User existingUser = entityManager.find(User.class, savedUser.getEmail());
//
//        assertThat(user.getEmail()).isEqualTo(existingUser.getEmail());
//        assertThat(user.getUsername()).isEqualTo(existingUser.getUsername());
//        assertThat(user.getRoles()).isEqualTo(existingUser.getRoles());
//    }
//}