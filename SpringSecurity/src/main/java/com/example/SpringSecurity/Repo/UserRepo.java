package com.example.SpringSecurity.Repo;

import com.example.SpringSecurity.Model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserDetails,Integer> {

    Optional<UserDetails> findByname(String name);
}
