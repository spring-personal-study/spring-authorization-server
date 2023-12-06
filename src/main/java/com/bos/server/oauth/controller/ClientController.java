package com.bos.server.oauth.controller;

import com.bos.server.oauth.model.dto.ClientRegistrar;
import com.bos.server.oauth.util.AuthorizationThreadLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final AuthorizationThreadLocal authorizationThreadLocal;

    // TODO:: Impl
    @PostMapping("/client/register")
    public ClientRegistrar.ClientRegistrationResponse registerClient(ClientRegistrar.ClientRegistrationRequest request) {
        String authorizationInfo = authorizationThreadLocal.getAuthorizationInfo();
        System.out.println("authorizationThreadLocal = " + authorizationInfo);
        return null;
    }

    public ClientRegistrar.ClientRegistrationResponse retrieveClient(String registrationAccessToken, String registrationClientUri) {
        return null;
    }
}
