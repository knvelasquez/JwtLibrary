package com.jwtlibrary.application;

import com.jwtlibrary.common.CurrentDateTime;
import com.jwtlibrary.domain.JwtDecoder;
import com.jwtlibrary.model.SecurityKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class JwtDecoderService implements JwtDecoder {
    private static final Logger logger = LogManager.getLogger(JwtDecoderService.class);
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private static final String COMPANY_CLAIM = "Company";
    private static final String USERNAME_CLAIM = "UserName";
    private static final String PRIVATE_KEY_CLAIM_KEY = "PRIVATE_KEY";
    private static final String SHIP_ID_CLAIM_KEY = "SHIP_ID";
    private static final String CREATED_CLAIM_KEY = "iat";
    private static final String EXPIRE_CLAIM_KEY = "exp";

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public SecurityKey decode(String jwt) {
        String BEARER = "Bearer ";
        Key key = getSignInKey();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt.replace(BEARER, ""))
                .getBody();
        //map keys from claims
        String company = (String) claims.get(COMPANY_CLAIM);
        String subject = claims.getSubject();
        String username = (String) claims.get(USERNAME_CLAIM);
        List<String> extraClaims = (List<String>) claims.get("authorities");
        LocalDateTime created = CurrentDateTime.from((Integer) claims.get(CREATED_CLAIM_KEY));
        LocalDateTime expire = CurrentDateTime.from((Integer) claims.get(EXPIRE_CLAIM_KEY));
        return new SecurityKey(company, subject, username, extraClaims, created, expire);
    }
}
