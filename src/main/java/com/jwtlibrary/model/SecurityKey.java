package com.jwtlibrary.model;

import java.time.LocalDateTime;
import java.util.List;

public class SecurityKey {
    private final String company;
    private final String subject;
    private final String username;
    private final List<String> extraClaims;
    private final LocalDateTime created;
    private final LocalDateTime expire;

    public SecurityKey(String company, String subject, String username, List<String> extraClaims, LocalDateTime created, LocalDateTime expire) {
        this.company = company;
        this.subject = subject;
        this.username = username;
        this.extraClaims = extraClaims;
        this.created = created;
        this.expire = expire;
    }

    public String getCompany() {
        return company;
    }

    public String getSubject() {
        return subject;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getExtraClaims() {
        return extraClaims;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getExpire() {
        return expire;
    }
}
