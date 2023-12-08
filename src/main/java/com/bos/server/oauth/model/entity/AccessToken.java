package com.bos.server.oauth.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "oauth_access_token")
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "access_token_value", length = 4000)
    private String tokenValue;

    @Column(name = "access_token_issued_at")
    private Instant tokenIssuedAt;

    @Column(name = "access_token_expires_at")
    private Instant tokenExpiresAt;

    @Column(name = "access_token_metadata", length = 2000)
    private String tokenMetadata;

    @Column(name = "access_token_type")
    private String tokenType;

    @Setter
    @Column(name = "access_token_scopes", length = 1000)
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

}
