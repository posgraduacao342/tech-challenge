CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE usuarios_papeis (
      id UUID NOT NULL DEFAULT uuid_generate_v4(),
      id_usuario UUID NOT NULL,
      id_papel UUID NOT NULL,
      CONSTRAINT usuarios_papeis_pkey PRIMARY KEY (id),
      CONSTRAINT fk_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios (id),
      CONSTRAINT fk_papeis FOREIGN KEY (id_papel) REFERENCES papeis (id)
);