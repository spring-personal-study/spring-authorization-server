package com.bos.server.oauth.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_USER")
public class ResourceOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "ID", length = 20, nullable = false, unique = true)
    private String resourceOwnerId;

    @Column(name = "PASSWORD", length = 500, nullable = false)
    private String password;

    public ResourceOwner(Long id, String resourceOwnerId, String password) {
        this.id = id;
        this.resourceOwnerId = resourceOwnerId;
        this.password = password;
    }
}
