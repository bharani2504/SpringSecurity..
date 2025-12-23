package com.example.SpringSecurity.Controller;


import com.example.SpringSecurity.Model.UserDetails;
import com.example.SpringSecurity.Repo.UserRepo;
import com.example.SpringSecurity.Service.UserService;
import com.example.SpringSecurity.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtils jwtutils;

    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);
    @PostMapping("/register")
    public ResponseEntity<String>createuser(@RequestBody Map<String,String> user){

        String name=user.get("name");
        String password= passwordEncoder.encode(user.get("password"));

        if(userRepo.findByname(name).isPresent()){
            return new ResponseEntity<>("user already exsists",HttpStatus.CONFLICT);
        }

        userService.createuser(UserDetails.builder().name(name).password(password).build());
        return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginuser(@RequestBody Map<String,String> user){
        String name=user.get("name");
        String password=user.get("password");

      var userOptional=userRepo.findByname(name);
      UserDetails User=userOptional.get();

      if(!passwordEncoder.matches(password,User.getPassword())){
          return new ResponseEntity<>("Invalid User",HttpStatus.UNAUTHORIZED);
      }
      String token =jwtutils.generatetoken(name);
        return ResponseEntity.ok(token);

    }



}
