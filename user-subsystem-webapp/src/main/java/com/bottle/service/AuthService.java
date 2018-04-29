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

    private UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    //Sample method to construct a JWT
    public String createJWT(String id, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        String secretKey = "JKKJfkjdfdskjf";

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
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

    public RespAuthDTO getTokenByLogin(String login){
        RespAuthDTO respAuthDTO = new RespAuthDTO();
        String id = userService.getIdByLogin(login).toString();
        String token = createJWT(id,0);
        respAuthDTO.setToken(token);
        return respAuthDTO;
    }
}
