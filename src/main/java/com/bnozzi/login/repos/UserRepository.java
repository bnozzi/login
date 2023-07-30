package com.bnozzi.login.repos;

import com.bnozzi.login.domain.User;
import com.bnozzi.login.model.UserDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailIgnoreCase(String email);

    User findByEmail(String email);


}
