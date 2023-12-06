package com.bos.server.oauth.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "oauth_oidc_token")
public class OidcToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oidc_id_token_value", length = 4000)
    private String oidcIdTokenValue;

    @Column(name = "oidc_id_token_issued_at")
    private Instant oidcIdTokenIssuedAt;

    @Column(name = "oidc_id_token_expires_at")
    private Instant oidcIdTokenExpiresAt;

    @Column(name = "oidc_id_token_metadata", length = 2000)
    private String oidcIdTokenMetadata;

    @Column(name = "oidc_id_token_claims", length = 2000)
    private String oidcIdTokenClaims;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorization_id", nullable = false)
    private Authorization authorization;

    public OidcToken(String oidcIdTokenValue, Instant oidcIdTokenIssuedAt, Instant oidcIdTokenExpiresAt, String oidcIdTokenMetadata, String oidcIdTokenClaims, Authorization authorization) {
        this.oidcIdTokenValue = oidcIdTokenValue;
        this.oidcIdTokenIssuedAt = oidcIdTokenIssuedAt;
        this.oidcIdTokenExpiresAt = oidcIdTokenExpiresAt;
        this.oidcIdTokenMetadata = oidcIdTokenMetadata;
        this.oidcIdTokenClaims = oidcIdTokenClaims;
        this.authorization = authorization;
    }
}
