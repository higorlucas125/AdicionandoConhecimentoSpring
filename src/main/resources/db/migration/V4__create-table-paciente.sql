CREATE TABLE pacientes (
                           id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           nome VARCHAR(100) NOT NULL,
                           email VARCHAR(100) NOT NULL UNIQUE,
                           telefone VARCHAR(15),
                           cpf VARCHAR(11) NOT NULL UNIQUE,
                           logradouro VARCHAR(100),
                           bairro VARCHAR(100),
                           cep VARCHAR(9),
                           numero VARCHAR(20),
                           complemento VARCHAR(100),
                           cidade VARCHAR(100),
                           uf CHAR(2),
                           ativo BOOLEAN NOT NULL
);