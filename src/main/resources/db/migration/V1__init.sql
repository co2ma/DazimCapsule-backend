CREATE TABLE capsule
(
    id              SERIAL NOT NULL,
    name            VARCHAR(255) NULL,
    email           VARCHAR(255) NULL,
    body            VARCHAR(255) NULL,
    unique_link     VARCHAR(255) NULL,
    expiration_date date NULL,
    is_processed    BIT(1) NOT NULL,
    CONSTRAINT pk_capsule PRIMARY KEY (id)
);