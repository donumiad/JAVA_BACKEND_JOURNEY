# API de Estoque

API REST desenvolvida durante o Módulo 6 do roadmap de Backend Java.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web MVC
- Spring JDBC
- PostgreSQL
- Maven
- Bean Validation
- Spring Boot Actuator

## Estrutura inicial

- `controller`: recebe requisições HTTP;
- `service`: coordena os casos de uso;
- `repository`: fornece acesso aos dados;
- `model`: representa objetos do domínio;
- `dto`: define os contratos de entrada e saída da API.

## Como executar

Configure as variáveis de ambiente:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

Depois execute a classe `EstoqueApiApplication`.

## Endpoints atuais

- `GET /api/ping`
- `GET /api/produtos`
- `GET /api/produtos/{id}`
- `GET /api/produtos?nome={trecho}`
- `GET /actuator/health`