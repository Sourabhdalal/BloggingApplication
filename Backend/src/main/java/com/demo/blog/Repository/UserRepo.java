package com.demo.blog.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.demo.blog.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>
{
	@Query("SELECT u FROM User u WHERE u.name = ?1 AND u.password = ?2")
    Optional<User> findByUsernameAndPassword(String username, String password);
}


