package br.com.todo.todo.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.todo.todo.models.User;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create().withIssuer("login-auth").withSubject(user.getId().toString())
                    .withExpiresAt(this.generateExpire()).sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while authenticating");
        }
    }

    public UUID validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var validation = JWT.require(algorithm).withIssuer("login-auth").build().verify(token).getSubject();
            return UUID.fromString(validation);
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant generateExpire() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3"));
    }
}
