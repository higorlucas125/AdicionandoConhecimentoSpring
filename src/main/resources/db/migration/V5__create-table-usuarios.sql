CREATE TABLE usuarios (
                          id BIGINT not null AUTO_INCREMENT PRIMARY KEY,
                          login VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL
);