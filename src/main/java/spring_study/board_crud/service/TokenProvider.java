package spring_study.board_crud.service;

import java.util.Base64;
import java.util.Date;
import java.util.Calendar;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;

@Getter
@Setter
@Service

public class TokenProvider {
    private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private String secret = Base64.getEncoder().encodeToString(key.getEncoded());

    public String createToken() {
        return Jwts.builder()
                .signWith(key)
                .compact();
    }

    

    public boolean validateToken(String jwtToken) throws JwtException {
        try {
            Jws<Claims> claims = Jwts.parser()
                                     .setSigningKey(key)
                                     .parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration().before(new Date()) == false;
        } catch (JwtException e) {
            e.printStackTrace();
        }
        return false;
    }
}