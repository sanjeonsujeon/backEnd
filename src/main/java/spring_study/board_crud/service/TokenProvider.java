package spring_study.board_crud.service;

import java.util.Base64;
import java.util.Date;
import java.util.Calendar;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;

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
    private int tokenExpirationMsec;

    public String createToken(int tokenExpiration) {
        setTokenExpirationMsec(tokenExpiration);
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, tokenExpirationMsec);
        Date expiryDate = calendar.getTime();

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }
    public boolean validateToken(String authToken) throws JwtException {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(authToken).getBody();
            return true;
        } catch (JwtException e) {
            e.printStackTrace();
        }
        return false;
    }
}