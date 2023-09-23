package com.jwtlibrary.adapter.factory;

import com.jwtlibrary.application.JwtDecoderService;
import com.jwtlibrary.application.JwtEncoderService;
import com.jwtlibrary.domain.JwtDecoder;
import com.jwtlibrary.domain.JwtEncoder;
import com.jwtlibrary.domain.JwtFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultJwtFactory implements JwtFactory {
    private final JwtDecoderService jwtDecoderService;
    private final JwtEncoderService jwtEncoderService;

    public DefaultJwtFactory(JwtDecoderService jwtDecoderService, JwtEncoderService jwtEncoderService) {
        this.jwtDecoderService = jwtDecoderService;
        this.jwtEncoderService = jwtEncoderService;
    }

    @Override
    public JwtDecoder getDecoder() {
        return jwtDecoderService;
    }

    @Override
    public JwtEncoder getEncoder() {
        return jwtEncoderService;
    }
}
