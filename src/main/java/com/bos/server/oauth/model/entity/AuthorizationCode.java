package com.bos.server.oauth.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "oauth_authorization_code")
public class AuthorizationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authorization_code_value", length = 4000)
    private String authorizationCodeValue;

    @Column(name = "authorization_code_issued_at")
    private Instant authorizationCodeIssuedAt;

    @Column(name = "authorization_code_expires_at")
    private Instant authorizationCodeExpiresAt;

    @Column(name = "authorization_code_metadata", length = 2000)
    private String authorizationCodeMetadata;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorization_id", nullable = false)
    private Authorization authorization;

    public AuthorizationCode(String authorizationCodeValue, Instant authorizationCodeIssuedAt, Instant authorizationCodeExpiresAt, String authorizationCodeMetadata, Authorization authorization) {
        this.authorizationCodeValue = authorizationCodeValue;
        this.authorizationCodeIssuedAt = authorizationCodeIssuedAt;
        this.authorizationCodeExpiresAt = authorizationCodeExpiresAt;
        this.authorizationCodeMetadata = authorizationCodeMetadata;
        this.authorization = authorization;
    }
}
