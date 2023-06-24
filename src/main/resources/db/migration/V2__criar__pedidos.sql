CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE pedidos (
                          id UUID NOT NULL DEFAULT uuid_generate_v4(),
                          status_pedido varchar(50) NOT NULL,
                          preco money NOT NULL,
                          status_pagamento varchar(50) NOT NULL,
                          data_recebimento timestamptz,
                          data_criacao timestamptz NOT NULL,
                          data_delecao timestamptz,
                          data_atualizacao timestamptz,
                          CONSTRAINT pedidos_pkey PRIMARY KEY (id)
)