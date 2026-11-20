# Estoque API · Especificação de requisitos v1.0

**Estado** Proposta inicial para o projeto de formação de Raimundo Neto.  
**Objetivo** Permitir que uma loja saiba quanto tem de cada produto e explique as alterações de saldo pelo histórico.  
**Usuário principal** Operador de estoque. O responsável pela loja também consulta catálogo, saldos e histórico. Esses papéis descrevem o trabalho das pessoas. As permissões serão especificadas no módulo de segurança.  
**Escopo inicial** Um estoque, produtos contados em unidades inteiras e preços em reais. Compras, vendas, faturamento e múltiplos depósitos ficam para uma eventual ampliação.

## Vocabulário e decisões iniciais propostas

- Produto é o item de catálogo, identificado por um ID estável. O nome pode mudar.
- Saldo é o número de unidades disponíveis. Todo produto começa com saldo zero. Para cadastrar mercadoria já disponível, registrar uma entrada inicial.
- Movimentação é o registro de uma entrada ou saída de um produto, com ID, quantidade e data/hora atribuída pelo sistema. Uma movimentação confirmada permanece no histórico. Uma correção é registrada por uma nova movimentação.
- Dimensões são opcionais. Quando informadas, largura, altura e profundidade devem estar presentes, em centímetros inteiros positivos.
- O preço deve ser maior que zero, com até duas casas decimais. O nome deve ter entre 1 e 300 caracteres após remover espaços das extremidades.
- A exclusão de um produto exige ausência de movimentações. Um produto com histórico pode ter seu cadastro corrigido preservando seu identificador.

## Backlog inicial e critérios de aceite

| ID | História de usuário | Critérios observáveis |
|---|---|---|
| RF01 | Como operador, quero cadastrar um produto para identificá-lo em movimentações posteriores. | Dados válidos geram um ID e saldo zero. Nome vazio, preço inválido ou medidas incompletas geram erro sem cadastrar o produto. |
| RF02 | Como operador, quero buscar um produto por ID e pesquisar o catálogo para localizar mercadorias. | Busca por ID retorna os dados, dimensões e saldo. ID ausente gera resposta de recurso não encontrado. A lista admite filtro parcial por nome, ignorando maiúsculas e minúsculas, e ordenação por nome ou preço, com desempate por ID. Filtro sem resultados devolve lista vazia. |
| RF03 | Como operador, quero corrigir os dados de um produto para manter o catálogo atualizado. | A alteração de nome, preço ou dimensões preserva ID, saldo e histórico. Os valores passam pelas mesmas validações do cadastro. |
| RF04 | Como operador, quero remover um cadastro sem uso para corrigir um engano. | Produto sem movimentações pode ser excluído e deixa de ser encontrado. Produto com histórico gera conflito e continua consultável. |
| RF05 | Como operador, quero registrar mercadoria recebida para atualizar a disponibilidade. | Produto existente e quantidade inteira positiva geram uma entrada com ID, data/hora e produto associado. O novo saldo é o anterior mais a quantidade. |
| RF06 | Como operador, quero registrar uma retirada para conhecer o saldo restante. | Quantidade inteira positiva até o saldo gera uma saída. Retirar o saldo inteiro deixa saldo zero. Quantidade acima do disponível gera conflito. |
| RF07 | Como responsável pela loja, quero consultar o histórico geral ou de um produto para conferir suas movimentações. | Cada item informa ID, produto, tipo, quantidade e data/hora. Os mais recentes aparecem primeiro, com desempate por ID decrescente. Produto existente sem movimentos tem histórico vazio. |

## Cenários que as implementações devem passar

| Caso | Dado | Quando | Então |
|---|---|---|---|
| CA01 · RF01/02 | Catálogo vazio | Cadastro Mouse por R$ 59,90 com medidas 6 × 4 × 10 cm e consulto o ID retornado | Recebo Mouse, as três medidas e saldo 0. |
| CA02 · RF05/07 | Mouse com saldo 10 | Registro entrada de 2 | O saldo passa a 12 e o histórico ganha uma única entrada de 2 ligada ao ID do Mouse. |
| CA03 · RF06/07 | Mouse com saldo 2 | Solicito saída de 3 | Recebo conflito, o saldo continua 2 e o histórico continua igual. |
| CA04 · RF05/06 | Produto inexistente ou quantidade zero | Tento movimentá-lo | Recebo erro correspondente à causa e nenhuma movimentação é confirmada. |
| CA05 · RF05/06 | Uma movimentação válida em andamento | A gravação do histórico falha | A alteração do saldo e o registro da movimentação são desfeitos juntos. |
| CA06 · RF06 | Um produto com uma unidade disponível | Dois operadores tentam retirar uma unidade ao mesmo tempo | Apenas uma saída é confirmada. O saldo termina em zero e há uma única nova saída. Verificar na etapa de concorrência. |

## Qualidade e evidência de entrega

| ID | Condição verificável | Etapa |
|---|---|---|
| RQ01 | Depois de reiniciar a API, o saldo e o histórico confirmados continuam consultáveis. Uma instalação em banco vazio cria o esquema a partir das migrações versionadas. | Persistência |
| RQ02 | O contrato HTTP documenta caminhos, campos, exemplos e erros. Dados inválidos retornam 400, ID inexistente 404 e conflito de negócio 409. A resposta de erro identifica a causa. | API |
| RQ03 | A suíte executa em banco isolado. Os testes cobrem sucesso, rejeição e rollback. A automação de integração executa a suíte em cada alteração proposta. | Testes e integração contínua |
| RQ04 | Na etapa de segurança, consultas e alterações exigem identidade autenticada. Um perfil de consulta consegue ler e recebe 403 ao tentar movimentar estoque. | Segurança |
| RQ05 | A versão de entrega inicia a partir de instruções reproduzíveis, expõe verificação de saúde e permite localizar nos logs uma requisição que falhou. | Operação e deploy |

## Uso ao longo do curso

Para cada entrega, indicar os IDs atendidos, registrar o teste que os cobre e demonstrar ao menos um fluxo pela API. Os critérios descrevem o comportamento prometido em qualquer implementação. As decisões sobre bibliotecas, mapeamentos, rotas e esquema físico pertencem à documentação técnica da respectiva versão.

O primeiro recorte entrega RF01 e a busca por ID de RF02. O segundo entrega RF05 e uma leitura de RF07. Completar os demais itens progressivamente. No módulo de segurança, detalhar permissões. Na etapa de operação, definir metas de desempenho a partir de carga e ambiente conhecidos.

Para acrescentar uma história, registrar ID, usuário, necessidade, finalidade, regras, exemplo de sucesso, exemplo de rejeição e etapa. Ao mudar uma regra já aceita, atualizar os cenários afetados e registrar a decisão no histórico abaixo.

| Versão | Decisão |
|---|---|
| 1.0 | Base proposta para as implementações do estoque. Premissas a validar incluem saldo inicial zero, preço positivo, medidas em conjunto e preservação do histórico. |
