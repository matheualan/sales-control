package com.salescontrol.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.salescontrol.model.security.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token}")
    private String secret;

    public String generateToken(Users users) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.create()
                    .withIssuer("sales-control")
                    .withSubject(users.getLogin())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new JWTCreationException("Error while generating token", e);
        }
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            String loginSubject = JWT.require(algorithm)
                    .withIssuer("sales-control")
                    .build()
                    .verify(token)
                    .getSubject();
            return loginSubject;
        } catch (JWTVerificationException e) {
            return null;
        }
    }

}