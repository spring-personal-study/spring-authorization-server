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
    @Column(name = "ID", length = 255, nullable = false, unique = true)
    private String id;

    @Column(name = "PASSWORD", length = 255, nullable = false)
    private String password;

    public ResourceOwner(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
