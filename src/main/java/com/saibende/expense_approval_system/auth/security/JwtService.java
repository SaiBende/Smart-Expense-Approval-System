package com.saibende.expense_approval_system.auth.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {

        @Value("${jwt.secret}")
        private String secret;

        @Value("${jwt.expiration}")
        private long expiration;

        public String generateToken(String email) {

                SecretKey key = Keys.hmacShaKeyFor(
                                secret.getBytes(StandardCharsets.UTF_8));

                return Jwts.builder()
                                .subject(email)
                                .issuedAt(new Date())
                                .expiration(
                                                new Date(
                                                                System.currentTimeMillis() + expiration))
                                .signWith(key)
                                .compact();
        }

        private SecretKey getSigningKey() {

                return Keys.hmacShaKeyFor(
                                secret.getBytes(StandardCharsets.UTF_8));

        }

        public String extractUsername(String token) {

                return extractAllClaims(token)
                                .getSubject();
        }

        private Claims extractAllClaims(String token) {

                return Jwts.parser()
                                .verifyWith(getSigningKey())
                                .build()
                                .parseSignedClaims(token)
                                .getPayload();
        }

        public boolean isTokenValid(
                        String token,
                        String email) {

                String extractedEmail = extractUsername(token);

                return extractedEmail.equals(email);
        }
}