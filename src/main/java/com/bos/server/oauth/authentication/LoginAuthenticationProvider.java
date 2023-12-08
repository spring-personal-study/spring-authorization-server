package com.bos.server.oauth.authentication;

import com.bos.server.config.exception.common.BizException;
import com.bos.server.oauth.exception.ResourceOwnerCrudErrorCode;
import com.bos.server.oauth.model.dto.ResourceOwnerDto;
import com.bos.server.oauth.service.JpaOAuth2AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final JpaOAuth2AuthorizationService authorizationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return processUserAuthentication(
                String.valueOf(authentication.getPrincipal()),
                String.valueOf(authentication.getCredentials().toString())
        );
    }

    private Authentication processUserAuthentication(String roId, String password) {
        try {
            ResourceOwnerDto ro = authorizationService.login(roId, password);
            JwtAuthenticationToken token = new JwtAuthenticationToken(
                    Jwt.withTokenValue(roId).headers(
                                    (headers) -> {
                                        headers.put("alg", "RS256");
                                        headers.put("typ", "JWT");
                                    }
                            ).claims((claims) -> claims.put("id", roId))

                            .build(),
                    List.of(new SimpleGrantedAuthority("SCOPE_photo"))
            );
            token.setDetails(ro);
            return token;
        } catch (BizException e) {
            if (e.getLocalizedMessage().equals(ResourceOwnerCrudErrorCode.RO_NOT_FOUND.getMsg())) {
                log.error(ResourceOwnerCrudErrorCode.RO_NOT_FOUND.getMsg());
                throw new BadCredentialsException(ResourceOwnerCrudErrorCode.RO_NOT_FOUND.getMsg());
            }
            log.error(ResourceOwnerCrudErrorCode.INCORRECT_ID_OR_PASSWORD.getMsg());
            throw new BadCredentialsException(ResourceOwnerCrudErrorCode.INCORRECT_ID_OR_PASSWORD.getMsg());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication) ||
                UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
