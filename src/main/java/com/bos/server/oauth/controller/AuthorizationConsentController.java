package com.bos.server.oauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class AuthorizationConsentController {

    private final RegisteredClientRepository registeredClientRepository;

    @GetMapping("/oauth/consent")
    public String consent(Principal principal, Model model,
                          @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
                          @RequestParam(OAuth2ParameterNames.SCOPE) String scope,
                          @RequestParam(OAuth2ParameterNames.STATE) String state) {

        Set<String> scopesToApprove = new LinkedHashSet<>();
        RegisteredClient registeredClient = this.registeredClientRepository.findByClientId(clientId);
        Set<String> scopes = registeredClient.getScopes();
        for (String requestedScope : StringUtils.delimitedListToStringArray(scope, " ")) {
            if (scopes.contains(requestedScope)) {
                scopesToApprove.add(requestedScope);
            }
        }

        model.addAttribute("clientId", clientId);
        model.addAttribute("clientName", registeredClient.getClientName());
        model.addAttribute("state", state);
        model.addAttribute("scopes", withDescription(scopesToApprove));
        model.addAttribute("principalName", principal.getName());
        model.addAttribute("redirectUri", registeredClient.getRedirectUris().iterator().next());

        return "consent";
    }

    private static Set<ScopeWithDescription> withDescription(Set<String> scopes) {
        Set<ScopeWithDescription> scopeWithDescriptions = new LinkedHashSet<>();
        for (String scope : scopes) {
            if (scope.equals("openid")) {
                continue;
            }
            scopeWithDescriptions.add(new ScopeWithDescription(scope));
        }
        return scopeWithDescriptions;
    }

    private static class ScopeWithDescription {
        private final String DEFAULT_DESCRIPTION = "cannot provide information for this permission.";
        private final Map<String, String> scopeDescriptions = new HashMap<>();
        private final String scope;
        private final String description;

        /*
        static {
            scopeDescriptions.put(
                    "openid",
                    "openid is a set of permissions that allow you to authenticate with the application."
            );
        }
        */

        ScopeWithDescription(String scope) {
            this.scope = scope;
            this.description = scopeDescriptions.getOrDefault(scope, DEFAULT_DESCRIPTION);
        }
    }
}