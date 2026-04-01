package com.example.demo.service;

import com.example.demo.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtService {
    private String secretKey = null;

    public String generateToken(User user) {
        Map<String, Objects> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuer("DCB")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decode = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(decode);
    }

    public String getSecretKey(){
        return secretKey = "key";
    }
}
