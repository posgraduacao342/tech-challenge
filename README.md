# tech-challenge

# Iniciar a aplicação pelo docker
## Acesse a pasta do docker
```sh
cd docker
```
## Rode o comando abaixo para subir a aplicação e o banco
```sh
 docker-compose up
```

## Rode o comando abaixo para buildar e subir a aplicação
```sh
 docker-compose up --build
```
## Para acessar o swagger
Abra o navegador e cole a url: http://localhost:8080/swagger-ui/index.html

# Inciar a aplicação pelo intellij
## Acesse a pasta do docker
```sh
cd docker
```
## Rode o comando abaixo para subir o banco
```sh
 docker-compose up -d db
```
## Comando para parar o container do banco
```sh
docker-compose stop db
```
## Iniciar a aplicação
Deve ser iniciar pelo botão de play do intellij

## Para acessar o swagger
Abra o navegador e cole a url: http://localhost:8080/swagger-ui/index.html
