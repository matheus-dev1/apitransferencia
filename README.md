
# API de Transferências Bancárias

Este projeto é uma aplicação RESTful que simula operações bancárias, como cadastro de clientes, transferência entre contas e consulta de histórico de transferências. Utiliza Spring Boot, Redis como banco de dados em memória, controle de concorrência nas transferências e implementa testes de mutação com PIT e testes unitários/integrados com JUnit 5.



## Funcionalidades
Cadastro de Clientes
- Endpoint para cadastrar um cliente com ID, nome, número da conta (único) e saldo inicial.
Listagem de Clientes
- Endpoint para listar todos os clientes cadastrados.
Busca de Cliente por Número da Conta
- Endpoint para buscar detalhes de um cliente utilizando o número da conta.
Transferência entre Contas
- Endpoint para realizar transferências entre duas contas, com controle de saldo e limite de R$ 10.000,00.
Histórico de Transferências
- Endpoint para consultar o histórico de transferências de uma conta, incluindo transferências bem-sucedidas e malsucedidas, ordenadas por data decrescente.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.3.4
- Redis (via RedisTemplate)
- JUnit 5 para testes unitários e de integração
- PIT para testes de mutação
- Springdoc OpenAPI (Swagger)

## Requisitos
Certifique-se de ter as seguintes ferramentas instaladas:
- Java 21
- Maven 3.8+
- Redis em execução local (WSL no Windows)
- Git
## Configuração e Execução

Clonar o Repositório
```bash
git clone https://github.com/matheus-dev1/apitransferencia.git
cd apitransferencia
```

## Configurar o Redis

O Redis não possui no windows de forma nativa, você precisa usar um WSL, então, segue os comando para baixar.

Habilitar WSL
```ubuntu
wsl --install
```
Instalar distrinuição Linux

Recomento o Ubuntu, que pode ser encontrado na Microsoft Store e pode ser baixado facilmente.

Instalar o Redis
```ubuntu
sudo apt update
sudo apt install redis-server
```

Iniciar o Redis
```ubuntu
redis-server
```

Iniciar o Redis com porta alternativa
```ubuntu
redis-server --port 6380
```

## Compilar e executar a aplicação
Para compilar e executar a aplicação, utilize os seguintes comandos

```ubuntu
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em ```http://localhost:8080```

## Colection 
Collection esta na raiz do projeto ```./collection.postman_collection.json```

## Testes Unitários
Para executar os testes unitários com o JUnit 5, use o comando
```bash
mvn test
```

Testes de Mutação com PIT
Para rodar os testes de mutação com o PIT, use o comando
```bash
mvn pitest:mutationCoverage
```

Os relatórios de mutação estarão disponíveis no diretório ```target/pit-reports/.```
## Endpoints Disponíveis

Cadastro de Clientes: ```POST /api/v1/clientes```

Body:
```json
{
  "id": "1",
  "nome": "Matheus Silva",
  "numeroConta": "123456",
  "saldo": 5000.0
}
```

Listagem de Clientes: ```GET /api/v1/clientes```

Busca de Cliente por Conta: ```GET /api/v1/clientes/{numeroConta}```

Transferência entre Contas: ```POST /api/v1/transferencias```

Body:
```json
{
  "contaOrigem": "123456",
  "contaDestino": "654321",
  "valor": 1500.0
}
```

## Documentação da API
Acesse ```http://localhost:8080/swagger-ui.html``` depois de compilar o projeto.
## Estrutura do Projeto
- ```src/main/java:``` Código da aplicação
- ```src/main/resources:``` Configurações de recursos (e.g., application.properties)
- ```src/test/java:``` Testes unitários e de integração
- ```target/:``` Diretório gerado durante a compilação, contém arquivos .class e relatórios