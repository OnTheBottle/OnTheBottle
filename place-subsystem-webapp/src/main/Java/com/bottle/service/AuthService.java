package com.bottle.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.UUID;

@Service
public class AuthService {

    private static final String secretKeyJWT = "JKKJfkjdfdskjf";


    public boolean isValidToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKeyJWT))
                    .parseClaimsJws(token).getBody();
        }
        catch (ExpiredJwtException | SignatureException e){
            return false;
        }
        return true;
    }

    public UUID getAuthId(String token) {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKeyJWT))
                    .parseClaimsJws(token).getBody();
        return UUID.fromString(claims.get("userId").toString());
    }

}
