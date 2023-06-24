CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE produtos (
                          id UUID NOT NULL DEFAULT uuid_generate_v4(),
                          nome varchar(255) NOT NULL,
                          preco money NOT NULL,
                          imagem bytea NOT NULL,
                          descricao varchar(500) NOT NULL DEFAULT now(),
                          categoria varchar(50) NOT NULL DEFAULT now(),
                          data_criacao timestamptz,
                          data_delecao timestamptz,
                          data_atualizacao timestamptz,
                          CONSTRAINT produtos_pkey PRIMARY KEY (id)
);