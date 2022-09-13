package ma.fukuro192.yabai.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Jwt = JSON Web Token
 */
@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    // see: https://youtu.be/mn5UZYtPLjg?t=529
    public String generateToken(String username) {
        Instant now = Instant.now();
        Instant expiration = now.plus(7, ChronoUnit.DAYS);
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(expiration))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return generateToken(user.getUsername());
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(jwtSecret)
                            .parseClaimsJws(token)
                            .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (UnsupportedJwtException e) {
            System.err.println("Unsupported JWT token");
        } catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token");
        } catch (SignatureException e) {
            System.err.println("Invalid JWT signature");
        } catch (ExpiredJwtException e) {
            System.err.println("Expired JWT token");
        } catch (IllegalArgumentException e) {
            System.err.println("JWT claims string is empty");
        }
        return false;
    }
    
}
