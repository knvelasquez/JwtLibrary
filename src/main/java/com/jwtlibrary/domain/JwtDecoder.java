package com.jwtlibrary.domain;

import com.jwtlibrary.model.SecurityKey;

public interface JwtDecoder {
    SecurityKey decode(String jwt);
}
