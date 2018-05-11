package com.bottle.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;
import io.jsonwebtoken.*;

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
