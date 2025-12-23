 Spring Security with JWT Authentication:

This project demonstrates how to secure REST APIs using Spring Security with JWT (JSON Web Token) based authentication.
It implements stateless security using a custom JWT filter instead of server-side sessions.

Features:

User authentication using Spring Security

JWT token generation & validation

Custom JWT filter to secure APIs

Password encryption using BCrypt

Secure APIs using Authorization: Bearer <token>

Technologies Used:

Java

Spring Boot

Spring Security

JWT (JSON Web Token)

BCrypt Password Encoder

Maven

REST APIs

JWT Authentication Flow:

User sends username & password to login API

Spring Security authenticates the user

Server generates a JWT token

Token is sent back to the client

Client sends JWT in Authorization header for every request

JWT Filter validates the token before accessing secured APIs
