package com.help_desk_app.service;

import com.help_desk_app.dto.UserCredentialDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Optional;

@Component
public class TokenHandler {
    private final static String KEY = "thethirdofmay2020";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";

    private static SecretKey secretKey;

    public TokenHandler() {
        byte[] decodedKey = Base64.getEncoder().encode(KEY.getBytes());
        secretKey = new SecretKeySpec(decodedKey, "AES");
    }

    public static Optional<UserCredentialDto> extractUserCredentials(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            Optional<UserCredentialDto> ob = Optional.of(new UserCredentialDto(body.get(EMAIL).toString(), body.get(PASSWORD).toString()));
            return ob;
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .claim(EMAIL, userDetails.getUsername())
                .claim(PASSWORD, userDetails.getPassword())
                .signWith(SignatureAlgorithm.ES512, secretKey)
                .compact();
    }

    public boolean validateToken(String authToken, UserDetails userDetails) {
        String signatureToMatch = generateToken(userDetails);
        return authToken.equals(signatureToMatch);
    }
}

