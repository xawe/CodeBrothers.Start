package com.codebrothers.services.auth.services;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.codebrothers.services.auth.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class TokenService implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private String subject = "com.codebrothers.services.auth";

    // generate token for user
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("User", user);
        return doGenerateToken(claims);
    }

    // Retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Validate token
    public Boolean validateToken(String token) {
        return !isTokenExpired(token.replace("Bearer ", "")) ;
    }

    // for retrieveing any information from token we will need the secret key
    // 1 - Use the Jwts.parserBuilder() method to create a JwtParserBuilder
    // instance.
    // 2 - Specify the SecretKey or asymmetric PublicKey you want to use to verify
    // the JWS signature.1
    // 3 - Call the build() method on the JwtParserBuilder to return a thread-safe
    // JwtParser.
    // 4 - Finally, call the parseClaimsJws(String) method with your jws String,
    // producing the original JWS.
    // 5 - The entire call is wrapped in a try/catch block in case parsing or
    // signature validation fails. We'll cover exceptions and causes for failure
    // later.
    private Claims getAllClaimsFromToken(String token) {
            return Jwts.parserBuilder() // (1)
                    .setSigningKey(key) // (2)
                    .build() // (3)
                    .parseClaimsJws(token)// (4)
                    .getBody(); // (5)
    }

    // Check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // while creating the token -
    private String doGenerateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(key)
                .compact();
    }
}