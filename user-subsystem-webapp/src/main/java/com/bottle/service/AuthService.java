package com.bottle.service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import com.bottle.model.dto.response.RespAuthDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthService {

    private static final String secretKeyJWT = "JKKJfkjdfdskjf";
    private static final long ttlMillis = 86400000;


    private UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public String createJWT(String userId) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKeyJWT);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //Key signingKey = MacProvider.generateKey();

        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .claim("userId", userId)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKeyJWT))
                    .parseClaimsJws(token).getBody();
        }
        catch (ExpiredJwtException e){
            return false;
        }
        return true;
    }

    public String getTokenByLogin(String login) {
        String userId = userService.getIdByLogin(login).toString();
        return createJWT(userId);
    }

    public UUID getAuthId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKeyJWT))
                .parseClaimsJws(token).getBody();
        return UUID.fromString(claims.get("userId").toString());
    }

}
