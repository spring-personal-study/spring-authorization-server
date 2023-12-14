package com.bos.server.oauth.authentication;

import com.bos.server.oauth.model.dto.ResourceOwnerDto;
import com.bos.server.oauth.model.entity.ResourceOwner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.beans.ConstructorProperties;
import java.util.*;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrincipalDetails implements UserDetails, OAuth2User {

    private final String username;
    private final String password;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;

    public PrincipalDetails(ResourceOwnerDto resourceOwner) {
        this.username = resourceOwner.roId();
        this.password = resourceOwner.password();
    }

    @ConstructorProperties({"resourceOwner", "attributes"})
    public PrincipalDetails(ResourceOwnerDto resourceOwner, Map<String, Object> attributes) {
        this.username = resourceOwner.roId();
        this.password = resourceOwner.password();
        this.attributes = attributes;
    }

    public void setAuthorities(List<?> authorities) {
        this.authorities = Collections.emptyList();
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
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return username;
    }
}