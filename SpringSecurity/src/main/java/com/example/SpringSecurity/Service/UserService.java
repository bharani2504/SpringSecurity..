package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Model.UserDetails;
import com.example.SpringSecurity.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserDetails createuser(UserDetails user){
        return userRepo.save(user);
    }

    public UserDetails getuser(Integer id){
        return userRepo.findById(id).orElseThrow(()-> new RuntimeException("User details not found"));
    }

}
