package com.bos.server.oauth.model.dto;

import com.bos.server.oauth.model.entity.ResourceOwner;

public record ResourceOwnerDto(
        String roId, String password, String roles, String authority
) {
    public static ResourceOwnerDto toObject(ResourceOwner owner) {
        return new ResourceOwnerDto(owner.getResourceOwnerId(), owner.getPassword(), owner.getRoles(), owner.getAuthority());
    }
}
