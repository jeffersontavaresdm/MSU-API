CREATE TABLE microsoft_security_update
(
    id                   BIGSERIAL PRIMARY KEY,
    key                  VARCHAR(50)  NOT NULL,
    alias                VARCHAR(50)  NOT NULL,
    document_title       VARCHAR(255) NOT NULL,
    severity             VARCHAR(255),
    initial_release_date TIMESTAMP WITH TIME ZONE,
    current_release_date TIMESTAMP WITH TIME ZONE,
    cvrf_url             VARCHAR(255) NOT NULL
);