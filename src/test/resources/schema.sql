CREATE TABLE oauth_client
(
    id                            bigint                      NOT NULL auto_increment,
    client_id                     varchar(255)                NOT NULL,
    client_id_issued_at           timestamp                   NOT NULL,
    client_secret                 varchar(255)  DEFAULT NULL,
    client_secret_expires_at      timestamp     DEFAULT NULL,
    client_name                   varchar(255)                NOT NULL,
    client_authentication_methods varchar(1000)               NOT NULL,
    authorization_grant_types     varchar(1000)               NOT NULL,
    redirect_uris                 varchar(1000) DEFAULT NULL,
    post_logout_redirect_uris     varchar(1000) DEFAULT NULL,
    scopes                        varchar(1000)               NOT NULL,
    client_settings               varchar(2000)               NOT NULL,
    token_settings                varchar(2000)               NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tb_user
(
    id       varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);