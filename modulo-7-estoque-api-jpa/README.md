# Módulo 6 — API REST de Estoque

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?logo=postgresql&logoColor=white)
![Testes](https://img.shields.io/badge/Testes-Em%20evolu%C3%A7%C3%A3o-yellow)

API REST desenvolvida como parte do [Java Backend Journey](../README.md). O projeto dá continuidade ao estudo de persistência com JDBC, agora utilizando **Spring Boot, Spring MVC e Spring JDBC**, com separação de camadas, documentação de endpoints e testes automatizados.

> **Situação atual:** funcionalidades principais da API implementadas; testes de Controllers adicionados; preparação e validação do ambiente para testes de integração com PostgreSQL em andamento.

## Objetivos do módulo

- Transformar operações de estoque em endpoints HTTP de uma API REST.
- Aplicar injeção de dependências e separar as responsabilidades das camadas.
- Utilizar PostgreSQL com `JdbcTemplate` e SQL parametrizado.
- Validar dados de entrada e padronizar respostas de erro.
- Gerenciar transações nas movimentações de estoque.
- Documentar e monitorar a aplicação.
- Praticar testes de Controllers e evoluir para testes de integração reais.

## Tecnologias

- Java 21
- Spring Boot 4.1.2
- Spring Web MVC
- Spring JDBC (`JdbcTemplate`)
- PostgreSQL
- Maven e Maven Wrapper
- Bean Validation
- Springdoc OpenAPI / Swagger UI
- Spring Boot Actuator
- JUnit, Mockito e MockMvc
- SLF4J / Logback

## Arquitetura

```text
Requisição HTTP
      |
      v
Controller      -> recebe requisições e devolve respostas HTTP
      |
      v
Service         -> executa regras de negócio e coordena operações
      |
      v
Repository      -> executa operações de persistência com JdbcTemplate
      |
      v
PostgreSQL      -> armazena produtos e movimentações de estoque
```

Pacotes principais em `src/main/java/br/com/raimundo/estoque/`:

| Pacote | Responsabilidade |
| --- | --- |
| `controller` | Rotas HTTP e comunicação com os Services |
| `service` | Casos de uso, validações de negócio e transações |
| `repository` | Interfaces, implementações JDBC e `RowMapper` |
| `model` | Objetos do domínio |
| `dto` | Contratos de entrada e saída da API |
| `exception` | Exceções e respostas de erro centralizadas |
| `config` | Configurações, incluindo OpenAPI |

## Funcionalidades implementadas

- Cadastro, consulta, atualização e exclusão de produtos.
- Listagem de produtos com filtro opcional por nome, paginação e ordenação.
- Registro de entradas e saídas de estoque.
- Consulta do histórico de movimentações.
- Verificação de estoque insuficiente e outras regras de negócio.
- Validação de requisições com Bean Validation.
- Respostas de erro em formato `ProblemDetail`.
- Transações em operações de movimentação.
- Logs de eventos da aplicação e endpoints de observabilidade.

## Endpoints

### Produtos

| Método | Rota | Finalidade | Sucesso |
| --- | --- | --- | --- |
| `GET` | `/api/produtos` | Listar, filtrar e paginar produtos | `200` |
| `GET` | `/api/produtos/{id}` | Consultar um produto por ID | `200` |
| `POST` | `/api/produtos` | Cadastrar produto | `201` |
| `PUT` | `/api/produtos/{id}` | Atualizar nome e preço | `200` |
| `DELETE` | `/api/produtos/{id}` | Remover produto | `204` |

Parâmetros opcionais de `GET /api/produtos`:

| Parâmetro | Padrão | Função |
| --- | --- | --- |
| `nome` | Sem filtro | Filtrar por parte do nome |
| `page` | `0` | Número da página, começando por zero |
| `size` | `10` | Quantidade de registros por página |
| `sort` | `nome` | Campo: `id`, `nome`, `preco` ou `estoque` |
| `direction` | `asc` | Ordenação ascendente ou descendente |

Exemplo:

```http
GET /api/produtos?nome=mouse&page=0&size=10&sort=preco&direction=desc
```

O retorno da listagem é um objeto paginado, não um array JSON simples:

```json
{
  "itens": [
    { "id": 1, "nome": "Mouse", "preco": 100.00, "estoque": 10 }
  ],
  "page": 0,
  "size": 10,
  "total": 1,
  "proximaPagina": false
}
```

Exemplo de cadastro:

```http
POST /api/produtos
Content-Type: application/json
```

```json
{
  "nome": "Teclado",
  "preco": 250.00,
  "estoque": 5
}
```

### Movimentações

| Método | Rota | Finalidade | Sucesso |
| --- | --- | --- | --- |
| `GET` | `/api/movimentacoes` | Listar histórico de movimentações | `200` |
| `POST` | `/api/produtos/{id}/entradas` | Registrar entrada no estoque | `201` |
| `POST` | `/api/produtos/{id}/saidas` | Registrar saída do estoque | `201` |

Exemplo de corpo para registrar uma movimentação:

```json
{ "quantidade": 3 }
```

A aplicação utiliza, entre outros, os status `400` para dados inválidos, `404` para produtos não encontrados e `409` para conflitos de regras de negócio, como estoque insuficiente.

### Utilitários e observabilidade

| Rota | Finalidade |
| --- | --- |
| `GET /api/ping` | Verificação simples de resposta da API |
| `GET /actuator/health` | Estado de saúde da aplicação |
| `GET /actuator/info` | Informações configuradas da aplicação |
| `/swagger-ui/index.html` | Interface para explorar a documentação OpenAPI |
| `/v3/api-docs` | Especificação OpenAPI em JSON |

## Pré-requisitos

- JDK 21
- PostgreSQL acessível localmente
- Banco de desenvolvimento `estoque_api` com as tabelas necessárias
- Variável de ambiente `DB_PASSWORD` configurada no ambiente que inicia a aplicação

As tabelas usadas pelo projeto são `produtos` e `movimentacoes_estoque`. A criação da estrutura ainda depende de preparação do PostgreSQL; **este módulo não possui migrações automatizadas com Flyway**. Há scripts SQL de referência no [Módulo 5](../modulo-5-estoque-jdbc/sql/), mas eles incluem outras tabelas e devem ser revisados antes de executar.

## Como executar

No Windows, abra o PowerShell na pasta `modulo-6-estoque-api`.

1. Confirme que o PostgreSQL está em execução e que o banco de desenvolvimento foi preparado.
2. Configure `DB_PASSWORD` como variável de ambiente **fora dos arquivos versionados**.
3. Execute:

```powershell
.\mvnw.cmd spring-boot:run
```

A configuração principal de `src/main/resources/application.properties` aponta para `jdbc:postgresql://localhost:5432/estoque_api` e utiliza o usuário `postgres` e a senha da variável `DB_PASSWORD`.

Com a aplicação iniciada, exemplos de acesso local:

```text
http://localhost:8080/api/ping
http://localhost:8080/api/produtos
http://localhost:8080/actuator/health
http://localhost:8080/swagger-ui/index.html
```

Para gerar o artefato com Maven:

```powershell
.\mvnw.cmd clean package
```

Os testes que necessitam do PostgreSQL de testes exigem que o ambiente correspondente esteja preparado.

## Testes automatizados

O módulo contém testes para duas finalidades complementares:

**Testes da camada web:** utilizam `@WebMvcTest`, `MockMvc` e `@MockitoBean` para verificar rotas, respostas JSON, status HTTP, validações e comunicação do Controller com um Service simulado, sem consultar o PostgreSQL.

**Teste de configuração de banco:** `ConexaoBancoTest` utiliza `@SpringBootTest`, `@ActiveProfiles("test")` e `JdbcTemplate` para consultar `SELECT current_database()` e confirmar que a conexão aponta para `estoque_api_test`.

O próximo passo é adicionar testes de integração de persistência com Repositories reais e rollback transacional.

Para executar especificamente a verificação de conexão no Windows:

```powershell
.\mvnw.cmd "-Dtest=ConexaoBancoTest" test
```

Para executar o conjunto de testes reconhecidos pelo Maven:

```powershell
.\mvnw.cmd test
```

### Banco de testes

O banco de testes deve ser **separado** do banco de desenvolvimento:

- Desenvolvimento: `estoque_api`
- Testes: `estoque_api_test`

O arquivo `src/test/resources/application-test.properties` deve apontar para o banco de testes e obter a senha por variável de ambiente, por exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/estoque_api_test
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
```

`@ActiveProfiles("test")` seleciona o perfil, mas não cria o banco nem suas tabelas. Testes com `@Transactional` geralmente executam rollback ao terminar quando as operações participam da transação gerenciada pelo Spring Test. Esse mecanismo não substitui o isolamento entre bancos.

> **Segurança:** nunca inclua senhas reais em commits. Antes de executar testes que alteram dados, confirme que a conexão aponta para `estoque_api_test`.

## Documentação complementar

O arquivo [`contrato-api.md`](./contrato-api.md) registra o **contrato inicial de referência**, criado antes das evoluções do Módulo 6. Alguns exemplos desse documento, como o retorno inicial em lista simples de produtos, diferem da implementação atual com paginação. O código e a documentação atualizada de endpoints acima devem prevalecer até que o contrato seja revisado.

## Próximos passos

- Concluir os testes de integração com PostgreSQL.
- Versionar a estrutura do banco de testes em scripts SQL apropriados.
- Consolidar os testes automatizados e revisar a documentação do contrato da API.
- Evoluir o projeto nas próximas etapas do roadmap, incluindo migrações e estratégias mais avançadas de testes.

---

[Voltar ao README geral](../README.md)
