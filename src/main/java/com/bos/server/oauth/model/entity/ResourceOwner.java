package com.bos.server.oauth.model.entity;

import com.bos.server.config.exception.common.BizException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.bos.server.oauth.exception.ResourceOwnerCrudErrorCode;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "oauth_resource_owner")
public class ResourceOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resource_owner_id", nullable = false)
    private String resourceOwnerId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "roles")
    private String roles;

    @Column(name = "authority")
    private String authority;

    public void login(PasswordEncoder passwordEncoder, String credentials) {
        if (!passwordEncoder.matches(credentials, this.password)) {
            throw new BizException(ResourceOwnerCrudErrorCode.INCORRECT_ID_OR_PASSWORD);
        }
    }

}
