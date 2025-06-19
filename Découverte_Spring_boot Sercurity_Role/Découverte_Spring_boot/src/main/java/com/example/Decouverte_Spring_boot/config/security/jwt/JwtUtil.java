package com.example.Decouverte_Spring_boot.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final JwtBuilder jwtBuilder;
    private final JwtParser jwtParser;
    private final JwtConfig jwtConfig;


    public JwtUtil(JwtConfig jwtConfig) {
        Key key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
        this.jwtBuilder = Jwts.builder().signWith(key);
        this.jwtParser = Jwts.parser().setSigningKey(key).build();
        this.jwtConfig = jwtConfig;
    }


    private Claims getClaimsFromToken(String token) {
        return jwtParser.parseSignedClaims(token).getPayload();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getIssuerFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuer);
    }

    public LocalDateTime getExpirationDateFromToken(String token) {
        Date date = getClaimFromToken(token, Claims::getExpiration);
        return date
                .toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public boolean isTokenExpired(String token) {
        LocalDateTime expirationDate = getExpirationDateFromToken(token);
        return expirationDate.isBefore(LocalDateTime.now());
    }

    public boolean validateToken(String token) {
        boolean isExpired = isTokenExpired(token);
        boolean isValidIssuer = getIssuerFromToken(token).equals(this.jwtConfig.getIssuer());

        return !isExpired && isValidIssuer;
    }

    private String generateToken(UserDetails userDetails, Map<String, Object> claims) {
        return jwtBuilder
                .claims()
                .add(claims)
                .expiration(new Date(System.currentTimeMillis() + this.jwtConfig.getExpiration() * 1000L))
                .and()
                .issuedAt(new Date())
                .issuer(this.jwtConfig.getIssuer())
                .subject(userDetails.getUsername())
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = Map.of(
                "role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray()
        );
        return generateToken(userDetails, claims);
    }
}
