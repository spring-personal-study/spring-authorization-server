package com.bos.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.TestSecurityContextHolder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthServerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient client;

    @Autowired
    void setMockMvc(MockMvc mockMvc) {
        this.client = MockMvcWebTestClient.bindTo(mockMvc)
                .build();
    }

    @Test
    @DisplayName("After user logged In, when to get Consent Page, then Ok")
    @Transactional
    void givenUserLoggedIn_whenToGetConsentPage_thenOk() {
        // given user logged in
        TestSecurityContextHolder.setAuthentication(jwtAuthenticationToken());

        // when to get consent page
        client.get()
                .uri("/oauth2/authorize?response_type=code&client_id=bluebird-default-client&scope=openid client.read&redirect_uri=http://127.0.0.1:" + port)
                .exchange()
                .expectBody()
                .consumeWith(System.out::println);
    }

    private JwtAuthenticationToken jwtAuthenticationToken() {
        return new JwtAuthenticationToken(jwt(), AuthorityUtils.createAuthorityList());
    }

    private Jwt jwt() {
        return Jwt.withTokenValue("token")
                .header("alg", "none")
                .audience(List.of("http://127.0.0.1:9000"))
                .expiresAt(Instant.MAX)
                .issuedAt(Instant.MIN)
                .issuer("http://127.0.0.1:9000")
                .jti("jti")
                .notBefore(Instant.MIN)
                .subject("mock-test-subject")
                .build();
    }
}
