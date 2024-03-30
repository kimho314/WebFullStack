CREATE TABLE authority
(
    user_id   VARCHAR(255) NOT NULL,
    authority VARCHAR(255) NOT NULL,
    CONSTRAINT pk_authority PRIMARY KEY (user_id, authority)
);

CREATE TABLE member
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    user_id      VARCHAR(100) NULL,
    password     VARCHAR(2000) NULL,
    name         VARCHAR(300) NULL,
    phone_number VARCHAR(100) NULL,
    email        VARCHAR(320) NULL,
    enabled      BIT(1) NULL,
    CONSTRAINT pk_member PRIMARY KEY (id)
);

CREATE TABLE member_authorities
(
    member_id             BIGINT       NOT NULL,
    authorities_authority VARCHAR(255) NOT NULL,
    authorities_user_id   VARCHAR(255) NOT NULL
);

ALTER TABLE member_authorities
    ADD CONSTRAINT uc_member_authorities_auauauusid UNIQUE (authorities_authority, authorities_user_id);

ALTER TABLE member_authorities
    ADD CONSTRAINT fk_memaut_on_authority FOREIGN KEY (authorities_user_id, authorities_authority) REFERENCES authority (user_id, authority);

ALTER TABLE member_authorities
    ADD CONSTRAINT fk_memaut_on_member FOREIGN KEY (member_id) REFERENCES member (id);