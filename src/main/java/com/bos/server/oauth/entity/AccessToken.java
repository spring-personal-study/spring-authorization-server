package com.bos.server.oauth.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "oauth_access_token")
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token_value", length = 4000)
    private String tokenValue;

    @Column(name = "token_issued_at")
    private Instant tokenIssuedAt;

    @Column(name = "token_expires_at")
    private Instant tokenExpiresAt;

    @Column(name = "token_metadata", length = 2000)
    private String tokenMetadata;

    @Column(name = "token_type")
    private String tokenType;

    @Column(name = "token_scopes", length = 1000)
    private String tokenScopes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorization_id", nullable = false)
    private Authorization authorization;

    @Builder
    public AccessToken(String tokenValue, Instant tokenIssuedAt, Instant tokenExpiresAt, String tokenMetadata, Authorization authorization) {
        this.tokenValue = tokenValue;
        this.tokenIssuedAt = tokenIssuedAt;
        this.tokenExpiresAt = tokenExpiresAt;
        this.tokenMetadata = tokenMetadata;
        this.authorization = authorization;
    }

    public void setTokenScopes(String tokenScopes) {
        this.tokenScopes = tokenScopes;
    }
}
