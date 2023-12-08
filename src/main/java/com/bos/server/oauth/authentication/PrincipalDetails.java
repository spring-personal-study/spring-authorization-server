package com.bos.server.oauth.authentication;

import com.bos.server.oauth.model.entity.ResourceOwner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrincipalDetails implements UserDetails, OAuth2User {

    private final ResourceOwner resourceOwner;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;

    public PrincipalDetails(ResourceOwner resourceOwner) {
        this.resourceOwner = resourceOwner;
    }

    @ConstructorProperties({"resourceOwner", "attributes"})
    public PrincipalDetails(ResourceOwner resourceOwner, Map<String, Object> attributes) {
        this.resourceOwner = resourceOwner;
        this.attributes = attributes;
    }

    public void setAuthorities(List<?> authorities) {
        if (resourceOwner.getRoles() == null) return;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<String> roles = List.of(resourceOwner.getRoles().split(","));
        roles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));
        this.authorities = grantedAuthorities;
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
        return resourceOwner.getPassword();
    }

    @Override
    public String getUsername() {
        return resourceOwner.getResourceOwnerId();
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
        return resourceOwner.getResourceOwnerId();
    }
}