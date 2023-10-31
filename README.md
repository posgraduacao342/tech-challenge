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

# Executar a aplicação pelo kubernetes

## Minikube
- minikube start
- minikube stop
- minikube status

## Executar os comandos para inicializar os pods respectivamente
- kubectl apply -f pipeline/dev/postgres
- kubectl apply -f pipeline/dev/application   


## Execute o comando abaixo para abrir a url que tem conexão com o pod
- minikube service tech-challenge-service

- Swagger: http://[Ip]:30001/swagger-ui/index.html

# Testar a aplicação

**OBS: Os endpoints que não possuem ação do cliente precisam de autenticação, use o usuário padrão para mandar as requests:**
- email: test@test.com
- senha: senha123
![autenticacao](./imagens/autenticacao.png)

## Acesse o swagger
Abra o navegador e cole a url: http://[Ip]:30001/swagger-ui/index.html

## Cadastro

- Abra o enpoint POST - /clientes
- Coloque as informações descritas no payload e envie a request

## Pedido
- Acesse o endpoint de GET - /produtos/porCategoria para filtrar os produtos por categoria
- Acesse o endpoint de produtos GET - /produtos para verificar e escolher os produtos a serem adicionados ao pedido
- Acesse o endpoint de pedido POST - /pedidos e informe os itens que deseja adicionar especificando o id do produto desejado

## Pagamento
- Acesse o enpoint de pagamento POST - /pagamentos e envia a request

## Fila de pedidos cozinha
- Acesse o enpoint de pedidos GET - /pedidos/fila e envia a request (Usar usuário padrão)

## Atualizar status do pedido (Cozinha)
- Status: EM_PREPARACAO / PRONTO / FINALIZADO
- Acesse o enpoint de pedidos GET - /pedidos/{pedidoId}/status e envia a request com o status desejado.

## Consultar status do pedido (Cliente)
- Acesse o enpoint de pedidos GET - /pedidos/{pedidoId} e envia a request.

## Mercado pago
### Pré requisitos
- Instalar o ngrok nesse [site](https://ngrok.com/download).
- Executar o ngrok `./ngrok http 8080`
- Pegar a url gerada(Ex: https://[urlNgrok]/pagamentos/mercado-pago/webhooks) e adicione na linha 54 do arquivo ./pipeline/dev/application/deployment.yaml 
- De um apply nas novas configurações do deployment:
```
 kubectl apply -f pipeline/dev/application/deployment.yaml
```

### Fluxo de pagamento
- Execute o comando `kubectl port-forward svc/tech-challenge-service 8080:80`
- Acesse o mercado pago com essa conta de teste:
```
usuario: TESTUSER2094400829
senha: T5QXrnlKgJ
```
- Faça um pedido no endpoint `POST - /pedido`
- Com o id do pedido execute uma chamada para o enpoint `/pagamentos/mercado-pago/qrcode`
- Pegue o QR Code em formato de texto que será retornado e cole nesse [site](https://www.qrcode-monkey.com/pt/?utm_source=google_c&utm_medium=cpc&utm_campaign=&utm_content=&utm_term=qrcode%20monkey_e&gclid=CjwKCAjw3dCnBhBCEiwAVvLcuzrWOjjWvrrH2V7GILt-d04D3pUgkeBTYBYvMzAmLMmNs2ZjzphfwBoC-rEQAvD_BwE#text) aperte o botão "Criar QR Code"

#### Fluxo de pagamento com sucesso
- Escaneie o QR Code e efetue o pagamento saldo da conta.

#### Fluxo de pagamento com erro
- Escaneie o QR Code e efetue o pagamento com o cartão de crédito cadastrado.
- Código de segurança é 123.
