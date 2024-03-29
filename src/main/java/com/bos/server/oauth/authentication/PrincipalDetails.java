package com.bos.server.oauth.authentication;

import com.bos.server.oauth.model.dto.ResourceOwnerDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.beans.ConstructorProperties;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrincipalDetails implements UserDetails, OAuth2User {

    private final ResourceOwnerDto resourceOwner;
    private Map<String, Object> attributes;
    @Setter
    private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

    public PrincipalDetails(ResourceOwnerDto resourceOwner) {
        this.resourceOwner = resourceOwner;
    }

    @ConstructorProperties({"resourceOwner", "attributes"})
    public PrincipalDetails(ResourceOwnerDto resourceOwner, Map<String, Object> attributes) {
        this.resourceOwner = resourceOwner;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return resourceOwner.password();
    }

    @Override
    public String getUsername() {
        return resourceOwner.roId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return resourceOwner.roId();
    }
}