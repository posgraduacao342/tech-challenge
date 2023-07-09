# tech-challenge

# Iniciar a aplicação pelo docker
## Acesse a pasta do docker
```sh
cd docker
```
## Rode o comando abaixo para buildar e subir a aplicação
```sh
 docker-compose up --build
```

## Rode o comando abaixo para subir a aplicação e o banco
```sh
 docker-compose up
```

# Testar a aplicação

**OBS: Os endpoints que não possuem ação do cliente precisam de autenticação, use o usuário padrão para mandar as requests:**
- email: test@test.com
- senha: senha123
![autenticacao](./imagens/autenticacao.png)

## Acesse o swagger
Abra o navegador e cole a url: http://localhost:8080/swagger-ui/index.html

## Cadastro

- Abra o enpoint POST - /clientes
- Coloque as informações descritas no payload e envie a request

## Pedido
- Acesse o enpoint de produtos GET - /produtos e envia a request
- Acesse o endpoint de pedido POST - /pedidos e informe os produtos desejados

## Pagamento
- Acesse o enpoint de pagamento POST - /pagamentos e envia a request

## Fila de pedidos cozinha
- Acesse o enpoint de pedidos GET - /pedidos/file e envia a request (Usar usuário padrão)

## Atualizar status do pedido (Cozinha)
- Status: EM_PREPARACAO / PRONTO / FINALIZADO
- Acesse o enpoint de pedidos GET - /pedidos/{pedidoId}/status e envia a request com o status desejado.

## Consultar status do pedido (Cliente)
- Acesse o enpoint de pedidos GET - /pedidos/{pedidoId} e envia a request.


