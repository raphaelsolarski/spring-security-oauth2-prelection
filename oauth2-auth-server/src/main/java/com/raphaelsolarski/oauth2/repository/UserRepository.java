package com.raphaelsolarski.oauth2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.raphaelsolarski.oauth2.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
