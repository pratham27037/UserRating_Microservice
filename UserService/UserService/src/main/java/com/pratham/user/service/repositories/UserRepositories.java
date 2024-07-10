package com.pratham.user.service.repositories;

import com.pratham.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User,String> {
    // can add functionalities of their own
}
