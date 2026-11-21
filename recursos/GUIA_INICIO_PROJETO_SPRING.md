# Como iniciar uma API Java com Spring Boot no IntelliJ

Guia de consulta do projeto Estoque API · Módulo 7  
Exemplo de referência: Java 21, Maven, Spring Boot 4.2.0, PostgreSQL, Flyway e JPA com Hibernate.

**Onde você está.** No módulo atual, o projeto já foi gerado, enviado ao GitHub e os dois bancos já foram criados. Para continuar a prática, comece no passo 4. Os três primeiros passos ficam como referência para a próxima vez que você iniciar um projeto.

## 1. Gerar o projeto

No IntelliJ, abra **File → New → Project → Spring Boot**. Esse assistente usa o Spring Initializr, o gerador que prepara os arquivos iniciais da aplicação. Também é possível fazer as mesmas escolhas em [start.spring.io](https://start.spring.io/), extrair o ZIP e abrir a pasta que contém o arquivo pom.xml.

| Campo | Escolha usada no estoque | O que define |
|---|---|---|
| Name e Location | estoque-api, na pasta modulo-7-estoque-api-jpa-v2 do repositório | Nome do projeto e local onde o IntelliJ gravará os arquivos. Confira o caminho final antes de criar. |
| Language e JDK | Java e JDK 21 | Linguagem e kit de desenvolvimento usado pela IDE. |
| Build system | Maven | Ferramenta que obtém bibliotecas, compila, testa e empacota o projeto. |
| Group e Artifact | br.com.raimundo e estoque-api | Identificadores do projeto no Maven. |
| Package name | br.com.raimundo.estoque | Pacote Java que contém a classe principal. |
| Java e Packaging | 21 e Jar | Versão-alvo do Java e formato do arquivo distribuído. |
| Spring Boot | 4.2.0 neste projeto | Escolha uma versão concreta no assistente. A expressão 4.2.x representa a família de versões. |

Na tela de dependências, selecione as capacidades que a primeira entrega precisa.

| Dependência | Papel no cadastro de produto |
|---|---|
| Spring Web | Receber o JSON enviado por HTTP e devolver a resposta. |
| Validation | Verificar campos recebidos, como nome obrigatório e preço positivo. |
| Spring Data JPA | Disponibilizar a infraestrutura JPA e o Hibernate. Neste módulo, escreveremos o repositório usando EntityManager. |
| PostgreSQL Driver | Permitir que a aplicação Java se conecte ao PostgreSQL. |
| Flyway Migration | Aplicar os arquivos SQL que criam e alteram as tabelas. No pom.xml deste projeto também aparece flyway-database-postgresql. |

Clique em **Create** e aguarde a importação do Maven.

## 2. Reconhecer os arquivos gerados

- **pom.xml** reúne a versão do Spring Boot, a versão do Java, as dependências e os plugins de construção.
- **mvnw.cmd** inicia o Maven Wrapper no Windows. Ele usa a versão de Maven configurada para o projeto.
- **src/main/java** contém o código da aplicação. A classe gerada é br.com.raimundo.estoque.EstoqueApiApplication. Seu método main chama SpringApplication.run e inicia o Spring. Crie as classes da API em subpacotes de br.com.raimundo.estoque para que a configuração padrão consiga localizá-las.
- **src/main/resources** contém configurações e arquivos usados pela aplicação, incluindo as migrações.
- **src/test/java** contém as classes de teste. **src/test/resources** pode ser criada quando o teste precisar de configuração própria.

No terminal PowerShell do IntelliJ, dentro da pasta que contém o pom.xml, execute:

```powershell
.\mvnw.cmd -version
.\mvnw.cmd compile
```

Confira se o primeiro comando informa Java 21 e se o segundo termina com BUILD SUCCESS. Até aqui, a compilação pode ser verificada antes de configurar o banco.

## 3. Preparar os bancos

No psql, com um usuário autorizado a criar bancos, execute uma vez para cada novo ambiente:

```sql
CREATE DATABASE estoque_jpa_dev;
CREATE DATABASE estoque_jpa_test;
```

O primeiro banco recebe os dados da API executada localmente. O segundo recebe os dados dos testes. Cada banco terá seu próprio histórico de migrações. No psql, o comando abaixo lista os dois bancos pelo prefixo:

```text
\l estoque_jpa_*
```

## 4. Conectar o Spring Boot ao PostgreSQL

Em **src/main/resources/application.properties**, configure:

```properties
spring.application.name=estoque-api
spring.datasource.url=jdbc:postgresql://localhost:5432/estoque_jpa_dev
spring.datasource.username=${ESTOQUE_DB_USER}
spring.datasource.password=${ESTOQUE_DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=validate
```

A URL identifica o banco de desenvolvimento. Ajuste a porta se sua instalação usar outra. As expressões entre chaves fazem o Spring obter o usuário e a senha do ambiente de execução. Em **Run → Edit Configurations**, abra a configuração da classe EstoqueApiApplication e adicione ESTOQUE_DB_USER e ESTOQUE_DB_PASSWORD ao campo **Environment variables**. Use uma conta que possa conectar ao banco e criar as tabelas no esquema escolhido. Guarde os valores na configuração local da IDE.

**Por que validate?** As mudanças no esquema serão descritas nos scripts do Flyway. Durante a inicialização, o Flyway aplica os scripts pendentes. Em seguida, o Hibernate verifica se as tabelas e colunas esperadas pelas entidades são compatíveis com o banco. Isso ajuda a detectar, por exemplo, uma coluna que existe no mapeamento Java e falta na tabela.

Para os testes de integração, crie **src/test/resources/application-test.properties**:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/estoque_jpa_test
```

O nome test nesse arquivo identifica um perfil, ou seja, um conjunto de configurações que você pode ativar. Na classe de teste gerada que contém @SpringBootTest, adicione @ActiveProfiles("test") e importe org.springframework.test.context.ActiveProfiles. O teste passa a usar a URL de teste e herda as demais propriedades. Disponibilize as duas variáveis de credenciais também ao processo que executa os testes. Uma variável definida apenas na configuração de execução da API pertence àquele processo.

**Como conferir.** Execute a classe principal pelo IntelliJ e procure estoque_jpa_dev na URL do PostgreSQL mostrada no console. Ao executar o teste, procure estoque_jpa_test no console daquela execução. A primeira inicialização pode informar que encontrou zero migrações enquanto você ainda não criou o primeiro arquivo SQL.

## 5. Traduzir o primeiro requisito para o banco

Comece pelo RF01, cadastrar um produto. O operador informa o nome, o preço e, opcionalmente, as dimensões completas. O sistema atribui o ID e inicia o saldo em zero. Esse enunciado define quais informações precisam sobreviver ao encerramento da aplicação.

Em **src/main/resources/db/migration**, crie **V1__criar_tabela_produtos.sql**. V1 é a versão, os dois sublinhados separam o número da descrição e a extensão identifica um script SQL. Um desenho compatível com o uso de sequência estudado no módulo é:

```sql
CREATE SEQUENCE produtos_id_seq INCREMENT BY 1;

CREATE TABLE produtos (
    id BIGINT PRIMARY KEY DEFAULT nextval('produtos_id_seq'),
    nome VARCHAR(300) NOT NULL CHECK (length(btrim(nome)) > 0),
    preco NUMERIC(10, 2) NOT NULL CHECK (preco > 0),
    estoque BIGINT NOT NULL DEFAULT 0 CHECK (estoque >= 0),
    largura_cm INTEGER,
    altura_cm INTEGER,
    profundidade_cm INTEGER,
    CONSTRAINT ck_produto_dimensoes CHECK (
        num_nonnulls(largura_cm, altura_cm, profundidade_cm) = 0
        OR (
            num_nonnulls(largura_cm, altura_cm, profundidade_cm) = 3
            AND largura_cm > 0 AND altura_cm > 0 AND profundidade_cm > 0
        )
    )
);
```

A sequência fornece IDs. O DEFAULT 0 e as restrições protegem o saldo no banco. A função num_nonnulls do PostgreSQL conta quantas medidas foram preenchidas. Isso permite representar tanto um produto sem medidas quanto um com três medidas positivas. Adotamos NUMERIC(10, 2) como escolha técnica de capacidade para o exemplo. Na API, valide até oito dígitos inteiros e duas casas decimais antes da gravação.

Reinicie a aplicação. No psql, confira o banco, a tabela e o registro de execução:

```text
\c estoque_jpa_dev
\d public.produtos
```

```sql
SELECT version, script, success
FROM flyway_schema_history
WHERE version = '1';
```

O resultado esperado é uma linha para V1 com success igual a t. Quando uma migração já foi aplicada e compartilhada, registre uma alteração posterior em uma nova versão.

## 6. Criar as primeiras classes a partir do cadastro

A organização abaixo é uma proposta para a nova API. No IntelliJ, clique com o botão direito em br.com.raimundo.estoque, escolha **New → Package** e crie cada subpacote quando chegar a sua vez. Dentro dele, use **New → Java Class**. A primeira operação a entregar é cadastrar um produto e consultar o ID retornado.

| Ordem | Arquivo no pacote base | O que escrever e por quê |
|---|---|---|
| 1 | model/Dimensoes.java | Reúna largura, altura e profundidade em centímetros. @Embeddable indica que os valores podem compor uma entidade. Mapeie cada atributo para sua coluna e inclua um construtor sem argumentos para o JPA. |
| 2 | model/Produto.java | Represente o produto com ID Long, nome String, preço BigDecimal, estoque long e dimensões opcionais. @Entity identifica a entidade, @Table indica produtos e @Embedded incorpora as dimensões. Use @Id, @GeneratedValue com SEQUENCE e @SequenceGenerator apontando para produtos_id_seq com allocationSize = 1. Inclua um construtor sem argumentos para o JPA e outro para o cadastro que recebe os dados, normaliza o nome e inicia o estoque em zero. |
| 3 | repository/ProdutoRepository.java | Defina a interface com salvar(Produto produto) retornando Produto e buscarPorId(Long id) retornando Optional<Produto>. O serviço passa a depender dessas duas operações. |
| 4 | repository/ProdutoRepositoryJpa.java | Implemente a interface. @Repository registra a classe no Spring. @PersistenceContext fornece o EntityManager. Em salvar(), chame persist(produto) e devolva o produto. Em buscarPorId(), use find(Produto.class, id) e envolva o retorno com Optional.ofNullable. |
| 5 | service/ProdutoService.java | Receba o repositório pelo construtor. Em cadastrar(), crie o produto e chame salvar() dentro de uma transação declarada com @Transactional do Spring. Em buscarPorId(), trate o ID ausente para que a API devolva 404. |
| 6 | controller/dto/ProdutoRequest.java e ProdutoResponse.java | Esses objetos descrevem os dados que entram e saem pela API. No pedido de cadastro, aceite nome, preço e dimensões opcionais. Valide também as três medidas do objeto interno quando fornecido. Na resposta, inclua ID e estoque. |
| 7 | controller/ProdutoController.java | @RestController recebe a requisição HTTP. O método de cadastro lê o JSON com @RequestBody, solicita validação com @Valid, chama o serviço e responde 201. O método de consulta lê o ID da rota e responde 200 com o produto encontrado. |

**Exemplo do passo 4.** Dentro da classe ProdutoRepositoryJpa, este é o corpo completo do método que grava um produto novo:

```java
@Override
public Produto salvar(Produto produto) {
    entityManager.persist(produto);
    return produto;
}
```

O parâmetro produto foi criado pelo método cadastrar() do serviço. O EntityManager passa a gerenciar essa instância. Com a estratégia de sequência escolhida, o Hibernate pode atribuir o ID antes de enviar o INSERT. A aplicação pode concluir a gravação quando a transação do serviço for confirmada. O retorno permite ao controller montar a resposta com o ID atribuído.

## 7. Conferir a primeira entrega pelo caminho do usuário

Com os passos de implementação concluídos e a aplicação em execução, envie **POST /produtos**. Exemplo de corpo JSON:

```json
{
  "nome": "Mouse",
  "preco": 59.90,
  "dimensoes": { "larguraCm": 6, "alturaCm": 4, "profundidadeCm": 10 }
}
```

No PowerShell, para um primeiro teste de cadastro sem dimensões e consulta subsequente:

```powershell
$produto = Invoke-RestMethod -Method Post -Uri 'http://localhost:8080/produtos' -ContentType 'application/json' -Body '{"nome":"Mouse","preco":59.90}'
Invoke-RestMethod -Uri "http://localhost:8080/produtos/$($produto.id)"
```

Confira se a segunda resposta traz o mesmo ID, o nome Mouse, o preço informado e estoque zero. Depois, envie um preço negativo e confira se a API devolve 400. Esses resultados verificam mais do que a inicialização do servidor: a requisição atravessou controller, serviço e repositório, e o produto pôde ser lido em uma nova requisição.

## Referências

- [Geração de projetos Spring Boot no IntelliJ](https://www.jetbrains.com/help/idea/spring-boot.html)
- [Spring Boot — configuração externa](https://docs.spring.io/spring-boot/reference/features/external-config.html)
- [Spring Boot — inicialização do banco e Flyway](https://docs.spring.io/spring-boot/how-to/data-initialization.html)
