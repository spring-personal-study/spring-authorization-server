package com.bos.server.oauth.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "oauth_user_code")
public class UserCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_code_value", length = 4000)
    private String userCodeValue;

    @Column(name = "user_code_issued_at")
    private Instant userCodeIssuedAt;

    @Column(name = "user_code_expires_at")
    private Instant userCodeExpiresAt;

    @Column(name = "user_code_metadata", length = 2000)
    private String userCodeMetadata;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorization_id", nullable = false)
    private Authorization authorization;

    public UserCode(String userCodeValue, Instant userCodeIssuedAt, Instant userCodeExpiresAt, String userCodeMetadata, Authorization authorization) {
        this.userCodeValue = userCodeValue;
        this.userCodeIssuedAt = userCodeIssuedAt;
        this.userCodeExpiresAt = userCodeExpiresAt;
        this.userCodeMetadata = userCodeMetadata;
        this.authorization = authorization;
    }
}
