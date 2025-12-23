package com.example.SpringSecurity.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
public class JwtUtils {

    private final String SECRET="Hi There I am currently Working in bluescope";
    private final long EXPIRATION=1000*60;
    private  final Key SecretKey= Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));



     public String generatetoken(String name){
       return Jwts.builder()
               .setSubject(name)
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
               .signWith(SecretKey, SignatureAlgorithm.HS256)
               .compact();

     }

     public String Extractname(String token){

         return Jwts.parserBuilder()
                 .setSigningKey(SecretKey)
                 .build()
                 .parseClaimsJws(token)
                 .getBody()
                 .getSubject();
     }

     public boolean validate(String token){
         try{
             Extractname(token);
             return true;
         }
         catch (JwtException exception){
             return false;
         }
     }

}
