// package com.campusconnect.campusconnect.config;

// import java.security.Key;
// import java.util.Date;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import com.campusconnect.campusconnect.enums.Role;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;


// @Component
// public class JwtUtil {
//     private final Key secretKey;
//     private final long expirationMs;

//     public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration) {
//         this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
//         this.expirationMs = expiration;
//     }

//     public String generateToken(String userName, Role role) {
//         return Jwts.builder()
//                 .setSubject(userName) 
//                 .claim("role", role.name())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
//                 .signWith(secretKey, SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     public String extractUsername(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(secretKey)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject(); 
//     } 

//     public Role extractRole(String token) {
//         String role = Jwts.parserBuilder()
//                         .setSigningKey(secretKey)
//                         .build()
//                         .parseClaimsJws(token)
//                         .getBody()
//                         .get("role", String.class);
        
//         return Role.valueOf(role);
//     }
// }
