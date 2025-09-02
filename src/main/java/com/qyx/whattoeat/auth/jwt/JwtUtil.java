package com.qyx.whattoeat.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

/**
 * Created by Yuxin Qin on 9/1/25
 */
@Component
public class JwtUtil {
    private final SecretKey key;
    private final long ttlMillis;

    public JwtUtil(org.springframework.core.env.Environment env) {
        String secret = env.getProperty("app.jwt.secret");
        this.key = Keys.hmacShaKeyFor(secret.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        long minutes = Long.parseLong(env.getProperty("app.jwt.ttl-minutes", "120"));
        this.ttlMillis = minutes * 60_000L;
    }

    public String generate(Long userId, String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(String.valueOf(userId))     // subject 存 userId
                .claim("email", email)                  // optional
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(ttlMillis)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
