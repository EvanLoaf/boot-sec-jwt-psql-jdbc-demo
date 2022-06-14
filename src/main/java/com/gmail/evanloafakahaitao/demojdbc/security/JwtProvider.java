package com.gmail.evanloafakahaitao.demojdbc.security;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class JwtProvider {

    @Value("${security.jwt.secret}")
    private String secret;
    @Value("${security.jwt.expiration.ms}")
    private int jwtExpirationMs;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date((new Date()).getTime() + jwtExpirationMs)
                )
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Token expired : {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT : {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Malformed JWT : {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("Invalid signature : {}", e.getMessage());
        } catch (Exception e) {
            log.error("Invalid token : {}", e.getMessage());
        }
        return false;
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
