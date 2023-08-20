# Variáveis para controle do Makefile
DOCKER_COMPOSE_FILE = docker-compose.yaml
JAVA_SERVICE_NAME = rinhadebackend
DOCKERHUB_URL ?= ""
DOCKERHUB_TOKEN ?= ""

.PHONY: build-app push-image start-services

# Comando para buildar a aplicação Java e criar uma imagem Docker
build-app:
	@docker build -t $(JAVA_SERVICE_NAME) .
	@echo "Build concluído. Prepare-se para enviar a imagem para o DockerHub."

# Comando para fazer push da imagem Docker para o DockerHub
push-image: build-app
	@read -p "Insira a URL do DockerHub (ex: username/my-java-app): " DOCKERHUB_URL;\
	read -p "Insira seu token do DockerHub: " DOCKERHUB_TOKEN;\
	echo $$DOCKERHUB_TOKEN | docker login -u $$DOCKERHUB_URL --password-stdin;\
	docker tag $(JAVA_SERVICE_NAME):latest $$DOCKERHUB_URL:latest;\
	docker push $$DOCKERHUB_URL:latest;\
	sed -i 's|image: oldimage:latest|image: '$$DOCKERHUB_URL':latest|' $(DOCKER_COMPOSE_FILE)
	@echo "Publish concluído. incie os serviços com o comando "make start-services"."

# Comando para iniciar os serviços usando Docker Compose
start-services:
	@docker-compose -f $(DOCKER_COMPOSE_FILE) up -d

start-services-local-dev:
	@docker-compose -f docker-compose-local.yaml up -d

make stop:
	@if [ -z "$$(docker ps -q)" ]; then \
		echo "Nenhum contêiner em execução."; \
	else \
		for i in $$(docker ps -q); do \
			docker stop $$i; \
		done; \
		echo "Todos os contêineres foram parados."; \
	fi