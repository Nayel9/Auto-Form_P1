package com.autoform.commandes.controllers;

import com.autoform.commandes.entities.User;
import com.autoform.commandes.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginRequest.getPassword())) {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
            Instant now = Instant.now();
            String jwt = Jwts.builder()
                    .subject(loginRequest.getUsername())
                    .issuedAt(Date.from(now))
                    .expiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
                    .signWith(key)
                    .compact();
            return ResponseEntity.ok(jwt);
        }
        return ResponseEntity.status(401).body("Identifiants invalides");
    }
}