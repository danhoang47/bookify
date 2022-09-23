/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package secure;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author toten
 */
public class JWTconvert {
    
    public static String encodeToJWT(String user_id, int role) {
        Instant now = Instant.now();
        byte[] secret = Base64.getDecoder().decode("ZGF5bGFwYXNzd29yZHNpZXVkYWlzaWV1dG9zaWV1a2hvbmdsbw0K");
        
        String jwt = Jwts.builder()
                    .claim("user_id", user_id)
                    .claim("role", role)
                    .setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plus(3, ChronoUnit.MINUTES)))
                    .signWith(Keys.hmacShaKeyFor(secret))
                    .compact();

        return jwt;
    }
    
        public static  Jws<Claims> decodeToJWT(String JWTString, String password) {
        Instant now = Instant.now();
        byte[] secret = Base64.getDecoder().decode(password);
        
        
        
        Jws<Claims> result = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(secret))
                    .parseClaimsJws(JWTString);

            return result;
    }
    
    
    public static void main(String[] args) {
        JWTconvert jwt = new JWTconvert();
        String jwtString = jwt.encodeToJWT("00f454a8-0d04-421d-b6f0-140bb33a3e3e", 1);
        System.out.println(jwtString);
        
        Jws<Claims> res = jwt.decodeToJWT(jwtString, "ZGF5bGFwYXNzd29yZHNpZXVkYWlzaWV1dG9zaWV1a2hvbmdsbw0K");
        System.out.println(res.getBody());
    }
}
