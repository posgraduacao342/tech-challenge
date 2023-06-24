CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE usuarios (
                          id UUID NOT NULL DEFAULT uuid_generate_v4(),
                          nome varchar(255) NOT NULL,
                          email varchar(300) NOT NULL,
                          cpf varchar(255) NOT NULL,
                          descricao varchar(500),
                          senha varchar(255) NOT NULL,
                          funcao varchar(255) NOT NULL,
                          data_criacao timestamptz,
                          data_delecao timestamptz,
                          data_atualizacao timestamptz,
                          CONSTRAINT usuarios_pkey PRIMARY KEY (id)
);