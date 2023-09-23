package com.jwtlibrary.application;

import com.jwtlibrary.domain.JwtEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtEncoderService implements JwtEncoder {
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private static final String AUTHORITIES = "authorities";
    private static final String COMPANY_CLAIM = "Company";
    private static final String USERNAME_CLAIM = "UserName";
    private static final String PRIVATE_KEY_CLAIM = "PrivateKey";
    private static final SignatureAlgorithm SIGNATURE = SignatureAlgorithm.HS256;//SignatureAlgorithm.HS512;

    @Override
    public String encode(String issuer, String subject, String company, String username, List<String> privileges) {
        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(subject)
                .setClaims(mapToExtraClaims(privileges))
                .claim(COMPANY_CLAIM, company)
                .claim(USERNAME_CLAIM, username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) //set token for 24 min
                .signWith(getSignInKey(), SIGNATURE)
                .compact();
    }

    @Override
    public String encode(String issuer, String subject, String company, String username, String privateKey, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(subject)
                .setClaims(extraClaims)
                .claim(COMPANY_CLAIM, company)
                .claim(PRIVATE_KEY_CLAIM, privateKey) //Set private key as a custom claim
                .claim(USERNAME_CLAIM, username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) //set token for 24 min
                .signWith(getSignInKey(), SIGNATURE)
                .compact();
    }

    private Map<String, Object> mapToExtraClaims(List<String> privileges) {
        Map<String, Object> extraClaims = new HashMap<>();
        if (!privileges.isEmpty()) {
            extraClaims.put(AUTHORITIES, privileges);
        }
        return extraClaims;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
