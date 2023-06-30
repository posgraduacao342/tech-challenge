INSERT INTO public.usuarios
(id, nome, email, cpf, descricao, senha, data_criacao, data_delecao, data_atualizacao)
VALUES('39f6a625-eab7-4224-ad92-f4ee5bd80768'::uuid, 'Matheus', 'test@test.com', '08578082907', NULL, '$2a$10$vyJGejf3Eg84dXS0lqVJhONmbJ0x8cl9c8e9bCb5tpnurkD0GDXqi', '2023-06-28 21:29:24.375', NULL, '2023-06-28 21:29:24.375');

INSERT INTO public.papeis
(id, papel)
VALUES('c8efa92b-8233-4025-be13-bc863b0efdd1'::uuid, 'ADMIN');

INSERT INTO public.usuarios_papeis
(id, id_usuario, id_papel)
VALUES('241788a4-7579-4ae7-bbc6-980e0460c041'::uuid, '39f6a625-eab7-4224-ad92-f4ee5bd80768'::uuid, 'c8efa92b-8233-4025-be13-bc863b0efdd1'::uuid);
