package com.example.blogprac.repository;

import com.example.blogprac.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SearchRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(long id);
}
