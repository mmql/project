package com.example.demo.util;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JwtUtil {
    public static void main(String[] args) {

    }

    private static final int EXPIRESSECOND = 480000;
    private static final String SECRET = "Abc@1#w23D";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public static String creatJWT(String username) {
        JwtBuilder builder = Jwts.builder()
                .claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRESSECOND))
                .signWith(SignatureAlgorithm.HS512, SECRET);
        return builder.compact();
    }

    private String creatJWT(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRESSECOND);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public static String getUsernameFromJWT(String jwt) {
        String username;
        try {
            Claims claims = getClaimsFromJWT(jwt);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public static Claims getClaimsFromJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
