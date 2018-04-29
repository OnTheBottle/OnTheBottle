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

@Service
public class AuthService {

    private String secretKeyJWT = "JKKJfkjdfdskjf";
    private long ttlMillis = 86400000;


    private UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    //Sample method to construct a JWT
    public String createJWT(String id) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);


        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKeyJWT);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //Key signingKey = MacProvider.generateKey();

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                //.setIssuedAt(now)
                //.setSubject(subject)
                //.setIssuer(issuer)
                //.claim("myName", "Hollo Tocken")
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKeyJWT))
                    .parseClaimsJws(token).getBody();
            System.out.println("Token is True");
            System.out.println("id: " + claims.getId());
            System.out.println("Expiration: " + claims.getExpiration());
        }
        catch (ExpiredJwtException e){
            System.out.println("Token is False");
            return false;
        }
        return true;
    }

    public String getTokenByLogin(String login) {
        String id = userService.getIdByLogin(login).toString();
        String token = createJWT(id);
        return token;
    }
}
