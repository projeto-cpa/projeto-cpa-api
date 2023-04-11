# Projeto Spring Boot usando Java 17 com Docker e Docker Compose

Este é um projeto Spring Boot que utiliza a versão 17 do Java e é executado dentro de contêineres Docker usando Docker Compose. Ele também inclui testes automatizados e unitários.

## Requisitos

Antes de começar, certifique-se de ter instalado:

- Java 17
- Docker
- Docker Compose

## Configuração

Para executar o projeto, siga os seguintes passos:

1. Clone este repositório em sua máquina local.
2. Navegue até o diretório raiz do projeto.
3. Execute o comando `docker-compose up` para criar e executar os contêineres Docker.
4. O aplicativo estará disponível em `http://localhost:8080`.

## Testes

Para executar os testes automatizados e unitários, execute o seguinte comando:

```
mvn clean test
```


Isso executará todos os testes na pasta `src/test/java`.

## Tecnologias

Este projeto utiliza as seguintes tecnologias:

- Spring Boot
- Java 17
- Docker
- Docker Compose

## Contribuição

Sinta-se à vontade para contribuir com este projeto. Basta seguir os seguintes passos:

1. Crie um fork deste repositório.
2. Clone o fork em sua máquina local.
3. Crie um branch para sua nova feature (`git checkout -b my-new-feature`).
4. Faça as alterações necessárias e faça o commit (`git commit -am 'Add some feature'`).
5. Faça o push para o branch (`git push origin my-new-feature`).
6. Crie um novo pull request.
