package com.flashpage.app.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.flashpage.app.security.user.AppUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final SecretKey key;
    private final long expirationMs;

    public JwtService(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-ms:86400000}") long expirationMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    public String generateToken(UserDetails userDetails) {
        AppUserDetails objetoAppUserDetails = (AppUserDetails) userDetails;

        Map<String, Object> claims = Map.of(
                "personaId", objetoAppUserDetails.getPersonaId(),
                "rol", objetoAppUserDetails.getRol().name()
        );

        Date dateNow = new Date();
        Date dateExpiration = new Date(dateNow.getTime() + expirationMs);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(dateNow)
                .setExpiration(dateExpiration)
                .signWith(key) // HS256 por default con SecretKey
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Long extractPersonaId(String token) {
        Object objetoObject = extractAllClaims(token).get("personaId");
        if (objetoObject instanceof Integer i) return i.longValue();
        if (objetoObject instanceof Long l) return l;
        if (objetoObject instanceof String s) return Long.valueOf(s);
        return null;
    }

    public String extractRol(String token) {
        Object objetoObject = extractAllClaims(token).get("rol");
        return objetoObject != null ? objetoObject.toString() : null;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username != null
                && username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date dateExpiration = extractAllClaims(token).getExpiration();
        return dateExpiration.before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
