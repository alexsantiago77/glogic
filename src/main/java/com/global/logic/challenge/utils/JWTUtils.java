package com.global.logic.challenge.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Freddy Paredes
 *
 * This class handle all JWT utils
 */
@Slf4j
@Component
public class JWTUtils {

    @Value("${app.security.signature}")
    private  String SECRET_KEY;
    @Value("${app.security.token.exp}")
    private  long EXPIRATION_TIME;

    public String generateToken(UserDetails userDetails, Map<String, Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Retrieve a specific claim by key
    public String getClaimByKey(String token, String key) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return (String) claims.get(key);
    }
}//class closure