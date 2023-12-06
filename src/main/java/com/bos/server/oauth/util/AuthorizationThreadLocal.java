package com.bos.server.oauth.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class AuthorizationThreadLocal {

    private final ThreadLocal<String> threadLocal;

    public AuthorizationThreadLocal() {
        threadLocal = new ThreadLocal<>();
    }

    public String getAuthorizationInfo() {
        String authorization = threadLocal.get();
        if (authorization == null) {
            WebClient webClient = WebClient.create();
            String accessToken = webClient
                    .post()
                    .uri("/oauth2/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .accept(MediaType.APPLICATION_FORM_URLENCODED)
                    .body("{ \"grant_type\":\"client_credentials\", \"scope\":\"client.create\"}", String.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            threadLocal.set(accessToken);
        }
        return authorization;
    }

    public void cleanUp() {
        threadLocal.remove();
    }
}
