package com.jwtlibrary.domain;

public interface JwtFactory {
    JwtDecoder getDecoder();

    JwtEncoder getEncoder();
}
