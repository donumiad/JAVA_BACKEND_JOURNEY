# Java Backend Journey

> Jornada de estudos e projetos práticos em desenvolvimento backend com Java.

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em%20desenvolvimento-yellow)

## Sobre o repositório

Este repositório registra minha evolução no estudo de **desenvolvimento backend Java**, combinando fundamentos da linguagem, orientação a objetos, persistência de dados, construção de APIs REST e testes automatizados.

O objetivo é transformar os assuntos estudados em projetos progressivos, com código versionado, documentação e decisões técnicas que possam ser explicadas e aprimoradas ao longo do aprendizado.

O repositório **não corresponde a uma única aplicação**: cada diretório representa uma etapa da jornada. Os projetos podem compartilhar conceitos e evoluir a partir de implementações anteriores.

## Projetos e evolução

| Etapa | Projeto ou foco | Conteúdos principais | Situação |
| --- | --- | --- | --- |
| Fundamentos (módulos anteriores) | Java Core, POO e coleções | Orientação a objetos, generics, streams, tratamento de exceções e organização de código | Conteúdos estudados e revisados ao longo da jornada |
| [Módulo 4 — Loja Simples](./modulo-4-loja-simples/) | Modelagem e SQL | Maven, PostgreSQL, tabelas relacionais, consultas e operações SQL | Etapa de estudo desenvolvida |
| [Módulo 5 — Estoque com JDBC](./modulo-5-estoque-jdbc/) | Aplicação Java com persistência | JDBC, padrão DAO, CRUD e transações com PostgreSQL | Base de persistência desenvolvida |
| [Módulo 6 — API de Estoque](./modulo-6-estoque-api/README.md) | API REST com Spring Boot | Controllers, Services, Repositories, validação, tratamento de erros, documentação e testes | **Em desenvolvimento — testes de integração** |

> **Nota:** a tabela descreve os diretórios e assuntos efetivamente desenvolvidos até aqui. Os próximos módulos do roadmap serão incorporados à medida que forem implementados.

## Estrutura do repositório

```text
JAVA_BACKEND_JOURNEY/
├── README.md                       # Visão geral da jornada
├── modulo-4-loja-simples/          # Maven, modelagem e SQL
├── modulo-5-estoque-jdbc/          # Persistência com JDBC e DAO
├── modulo-6-estoque-api/           # API REST com Spring Boot
│   ├── README.md                   # Documentação específica da API
│   ├── contrato-api.md             # Contrato inicial de referência
│   ├── pom.xml
│   └── src/
└── recursos/                      # Material de apoio e anotações
```

## Tecnologias e conceitos trabalhados

- **Java 21:** lógica, POO, coleções, generics, streams e exceções.
- **Maven:** organização do projeto, dependências e ciclo de build.
- **PostgreSQL e SQL:** modelagem relacional, consultas, integridade e transações.
- **JDBC e Spring JDBC:** acesso ao banco, consultas parametrizadas e mapeamento de resultados.
- **Spring Boot e Spring MVC:** injeção de dependências, camadas da aplicação e endpoints REST.
- **Bean Validation:** validação de dados recebidos pela API.
- **OpenAPI / Swagger UI:** documentação e exploração dos endpoints.
- **Spring Boot Actuator:** verificações de saúde e informações da aplicação.
- **JUnit, Mockito e MockMvc:** testes automatizados de Controllers e preparação de testes de integração.
- **Git e GitHub:** versionamento e acompanhamento da evolução dos projetos.

## Projeto em destaque: API de Estoque

O projeto atual evolui a aplicação de estoque para uma **API REST**. A implementação inclui cadastro e consulta de produtos, paginação e ordenação, atualização e exclusão, além do registro de entradas e saídas de estoque.

A arquitetura separa responsabilidades entre **Controller → Service → Repository → PostgreSQL**. A API utiliza DTOs para entrada e saída de dados, validações, tratamento centralizado de exceções e transações nas operações de movimentação.

Os testes da camada web utilizam `MockMvc` e Mockito. O ambiente de integração com PostgreSQL separado está sendo preparado e validado.

**Documentação e instruções de execução:** [README do Módulo 6](./modulo-6-estoque-api/README.md).

## Como explorar os projetos

Cada módulo possui sua própria estrutura e dependências. Para iniciar pelo projeto atual:

```bash
git clone https://github.com/donumiad/JAVA_BACKEND_JOURNEY.git
cd JAVA_BACKEND_JOURNEY/modulo-6-estoque-api
```

Siga as instruções de banco de dados, configuração e execução no [README da API](./modulo-6-estoque-api/README.md). O diretório raiz não possui um único comando de execução para todos os módulos.

## Objetivos de aprendizagem

Este repositório faz parte da minha preparação para atuar profissionalmente como **desenvolvedor backend Java**. O foco não é apenas concluir exercícios, mas compreender as decisões técnicas, escrever código testável, identificar erros, documentar funcionalidades e construir um portfólio progressivamente mais consistente.

## Contato

- [GitHub — donumiad](https://github.com/donumiad)
- [LinkedIn](https://www.linkedin.com/in/raimundo-neto-00a677157/)

---

*Repositório de estudos em evolução contínua. As funcionalidades e a documentação serão atualizadas conforme o avanço dos módulos.*
