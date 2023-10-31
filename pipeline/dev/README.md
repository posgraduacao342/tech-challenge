# Pipeline

## Minikube
- minikube start
- minikube stop
- minikube status

## Executar os comandos para inicializar os pods
- kubectl apply -f dev/application
- kubectl apply -f dev/postgress

## Comandos para vizualizar os pods executando
- kubectl get pod --watch

## Comando para limpar o cluster
- kubectl delete --all deployment
- kubectl delete --all pod
- kubectl delete --all secrets
- kubectl delete --all svc



## Execute o comando abaixo para abrir a url que tem conexão com o pod
- minikube service tech-challenge-service

- Swagger: http://<Ip>:30001/swagger-ui/index.html