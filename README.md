# Passos para execução do projeto

## Projeto Spring

### Iniciar os serviços na seguinte ordem:
* spring-cloud-config-server
* netflix-eureka-naming-server
* cidade-service
* client-service
* zuul-server


## Keycloak

### Executar os seguintes passos:

* Transformar o arquivo "run.sh" em um executável com o seguinte comando: `chmod +x run.sh`

* Iniciar o keycloak com o mysql a partir do arquivo "run.sh": './run.sh'

* Importar o heam-export.json no keycloak

## Postman

* Importar o arquivo Spring.postman_collection.json

### TODO

- [ ] Criar DTO para expor os objetos da forma adequada 
- [ ] Subir autenticação para a camada do zuul
- [ ] Criar o Dockerfile para cada projeto
- [ ] Configurar o Hystrix
- [ ] Configurar o Zipkin
- [ ] Documentação
- [ ] Corrigir os testes
