package com.bos.server.oauth.authentication;

import com.bos.server.oauth.repository.resourceowner.ResourceOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalService implements UserDetailsService {

    private final ResourceOwnerRepository resourceOwnerRepository;

    @Override
    public PrincipalDetails loadUserByUsername(String roId) {
        return new PrincipalDetails(resourceOwnerRepository.findByResourceOwnerId(roId));
    }
}
