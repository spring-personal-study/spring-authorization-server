package com.bos.server.oauth.authentication;

import com.bos.server.oauth.service.JpaOauth2ResourceOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalService implements UserDetailsService {

    private final JpaOauth2ResourceOwnerService resourceOwnerService;

    @Override
    public PrincipalDetails loadUserByUsername(String roId) {
        return new PrincipalDetails(resourceOwnerService.findByResourceOwnerId(roId));
    }
}
