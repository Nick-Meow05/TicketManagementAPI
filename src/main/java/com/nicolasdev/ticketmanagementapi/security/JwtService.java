package com.nicolasdev.ticketmanagementapi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(
    String username){

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(
                        System.currentTimeMillis()
                                + 86400000))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey(){

        return Keys.hmacShaKeyFor(
                secretKey.getBytes(
                        StandardCharsets.UTF_8));
    }

    public String extractUsername(
            String token){

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(
            String token,
            String username){

        String extractedUsername = extractUsername(token);

        return extractedUsername
                .equals(username)
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(
            String token){

        return extractExpiration(token)
                .before(new Date());

    }

    private Date extractExpiration(
            String token){

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }
}
