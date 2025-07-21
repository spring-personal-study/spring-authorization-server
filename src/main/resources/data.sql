INSERT INTO oauth_client (
    client_id,
    client_secret,
    client_name,
    client_authentication_methods,
    authorization_grant_types,
    redirect_uris,
    scopes,
    client_settings,
    token_settings
)
VALUES (
    'new-client',
    '{bcrypt}$2a$10$v.4/93wz4G9pD1s.2.qj9u6i/iN2.1s2.3s4.5t6.7u8.9v0.1w2', -- new-client-secret
    'New Client',
    'client_secret_basic',
    'authorization_code,refresh_token',
    'http://127.0.0.1:8081/login/oauth2/code/new-client',
    'openid,profile,message.read',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}',
    '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.reuse-refresh-tokens":true}'
);
