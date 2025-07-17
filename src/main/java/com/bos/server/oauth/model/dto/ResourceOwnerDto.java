package com.bos.server.oauth.model.dto;

import com.bos.server.oauth.model.entity.ResourceOwner;

public record ResourceOwnerDto(
        String id, String password
) {
    public static ResourceOwnerDto toObject(ResourceOwner owner) {
        return new ResourceOwnerDto(owner.getId(), owner.getPassword());
    }
}
