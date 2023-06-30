CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE papeis (
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    papel varchar(255) UNIQUE NOT NULL,
    data_criacao timestamptz NOT NULL DEFAULT now(),
    data_atualizacao timestamptz NOT NULL DEFAULT now(),
    data_delecao timestamptz,
    CONSTRAINT papeis_pkey PRIMARY KEY (id)
);