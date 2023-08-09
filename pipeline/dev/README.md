# Pipeline

## Minikube
- minikube start
- minikube stop
- minikube status

## Executar os comandos para inicializar os pods
- kubectl apply -f dev/application/secret.yaml
- kubectl apply -f dev/postgress/postgres-secret.yaml
- kubectl apply -f dev/postgress/postgres-service.yaml
- kubectl apply -f dev/postgress/postgres-deployment.yaml 
- kubectl apply -f dev/application/service.yaml
- kubectl apply -f dev/application/deployment.yaml 

## Execute o comando abaixo para abrir a url que tem conexão com o pod
- minikube service tech-challenge-service

- Swagger: http://<Ip>:30001/swagger-ui/index.html