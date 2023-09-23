package com.jwtlibrary.domain;

import java.util.List;
import java.util.Map;

public interface JwtEncoder {
    String encode(String issuer, String subject, String company, String username, List<String> privileges);

    String encode(String issuer, String subject, String company, String username, String privateKey, Map<String, Object> extraClaims);
}
