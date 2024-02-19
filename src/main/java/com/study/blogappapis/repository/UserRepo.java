package com.study.blogappapis.repository;

import com.study.blogappapis.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
