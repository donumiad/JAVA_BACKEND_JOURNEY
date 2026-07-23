# Contrato inicial da API de Estoque

Este documento define o comportamento esperado da API antes da implementação em Spring Boot.

---

## 1. Listar todos os produtos

**Método:** `GET`

**URI:** `/api/produtos`

**Objetivo:** consultar todos os produtos cadastrados.

**Entrada:** não possui corpo de requisição.

**Status de sucesso:** `200 OK`

**Exemplo de resposta:**

```json
[
  {
    "id": 1,
    "nome": "Teclado Mecânico",
    "preco": 250.00,
    "estoque": 15
  },
  {
    "id": 2,
    "nome": "Mouse Gamer",
    "preco": 120.00,
    "estoque": 30
  }
]
```

### Caso não existam produtos cadastrados

A API deve retornar `200 OK` com uma lista vazia:

```json
[]
```

---

## 2. Consultar produto por ID

**Método:** `GET`

**URI:** `/api/produtos/{id}`

**Objetivo:** consultar um produto específico pelo seu identificador.

**Entrada:** o ID do produto é informado na URI.

**Exemplo de requisição:**

```http
GET /api/produtos/5
```

**Status de sucesso:** `200 OK`

**Exemplo de resposta:**

```json
{
  "id": 5,
  "nome": "Webcam Full HD",
  "preco": 200.00,
  "estoque": 12
}
```

### Produto não encontrado

**Status:** `404 Not Found`

```json
{
  "status": 404,
  "detalhe": "Produto de ID 50 não encontrado"
}
```

---

## 3. Cadastrar produto

**Método:** `POST`

**URI:** `/api/produtos`

**Objetivo:** cadastrar um novo produto.

**Entrada:** dados do produto no corpo da requisição.

**Exemplo de requisição:**

```json
{
  "nome": "Teclado Mecânico",
  "preco": 250.00,
  "estoque": 15
}
```

O campo `id` não é enviado porque será gerado pelo banco de dados.

**Status de sucesso:** `201 Created`

**Exemplo de resposta:**

```json
{
  "id": 11,
  "nome": "Teclado Mecânico",
  "preco": 250.00,
  "estoque": 15
}
```

### Dados inválidos

Exemplos:

- nome vazio;
- preço igual ou menor que zero;
- estoque negativo.

**Status:** `400 Bad Request`

```json
{
  "status": 400,
  "detalhe": "Os dados do produto são inválidos"
}
```

---

## 4. Atualizar produto

**Método:** `PUT`

**URI:** `/api/produtos/{id}`

**Objetivo:** atualizar os dados de um produto existente.

**Entrada:** ID na URI e novos dados no corpo da requisição.

**Exemplo de requisição:**

```http
PUT /api/produtos/5
```

```json
{
  "nome": "Webcam Full HD Pro",
  "preco": 230.00,
  "estoque": 18
}
```

**Status de sucesso:** `200 OK`

**Exemplo de resposta:**

```json
{
  "id": 5,
  "nome": "Webcam Full HD Pro",
  "preco": 230.00,
  "estoque": 18
}
```

### Produto não encontrado

**Status:** `404 Not Found`

```json
{
  "status": 404,
  "detalhe": "Produto de ID 5 não encontrado"
}
```

### Dados inválidos

**Status:** `400 Bad Request`

```json
{
  "status": 400,
  "detalhe": "Os dados do produto são inválidos"
}
```

---

## 5. Remover produto

**Método:** `DELETE`

**URI:** `/api/produtos/{id}`

**Objetivo:** remover um produto pelo seu identificador.

**Entrada:** o ID do produto é informado na URI.

**Exemplo de requisição:**

```http
DELETE /api/produtos/5
```

**Status de sucesso:** `204 No Content`

A resposta de sucesso não possui corpo.

### Produto não encontrado

**Status:** `404 Not Found`

```json
{
  "status": 404,
  "detalhe": "Produto de ID 5 não encontrado"
}
```

---

## 6. Registrar entrada de estoque

**Método:** `POST`

**URI:** `/api/produtos/{id}/entradas`

**Objetivo:** aumentar o estoque do produto e registrar uma movimentação do tipo `ENTRADA`.

**Entrada:** ID do produto na URI e quantidade no corpo da requisição.

**Exemplo de requisição:**

```http
POST /api/produtos/5/entradas
```

```json
{
  "quantidade": 10
}
```

**Status de sucesso:** `201 Created`

**Exemplo de resposta:**

```json
{
  "id": 1,
  "produtoId": 5,
  "tipo": "ENTRADA",
  "quantidade": 10,
  "dataMovimentacao": "2026-07-20T14:30:00"
}
```

### Produto não encontrado

**Status:** `404 Not Found`

```json
{
  "status": 404,
  "detalhe": "Produto de ID 5 não encontrado"
}
```

### Quantidade inválida

A quantidade deve ser maior que zero.

**Status:** `400 Bad Request`

```json
{
  "status": 400,
  "detalhe": "A quantidade deve ser maior que zero"
}
```

---

## 7. Registrar saída de estoque

**Método:** `POST`

**URI:** `/api/produtos/{id}/saidas`

**Objetivo:** diminuir o estoque do produto e registrar uma movimentação do tipo `SAIDA`.

**Entrada:** ID do produto na URI e quantidade no corpo da requisição.

**Exemplo de requisição:**

```http
POST /api/produtos/5/saidas
```

```json
{
  "quantidade": 4
}
```

**Status de sucesso:** `201 Created`

**Exemplo de resposta:**

```json
{
  "id": 2,
  "produtoId": 5,
  "tipo": "SAIDA",
  "quantidade": 4,
  "dataMovimentacao": "2026-07-20T15:10:00"
}
```

### Produto não encontrado

**Status:** `404 Not Found`

```json
{
  "status": 404,
  "detalhe": "Produto de ID 5 não encontrado"
}
```

### Quantidade inválida

**Status:** `400 Bad Request`

```json
{
  "status": 400,
  "detalhe": "A quantidade deve ser maior que zero"
}
```

### Estoque insuficiente

**Status:** `409 Conflict`

```json
{
  "status": 409,
  "detalhe": "Estoque insuficiente para realizar a saída"
}
```

---

## 8. Listar movimentações de estoque

**Método:** `GET`

**URI:** `/api/movimentacoes`

**Objetivo:** consultar o histórico de entradas e saídas do estoque.

**Entrada:** não possui corpo de requisição.

**Status de sucesso:** `200 OK`

**Exemplo de resposta:**

```json
[
  {
    "id": 1,
    "produtoId": 5,
    "tipo": "ENTRADA",
    "quantidade": 10,
    "dataMovimentacao": "2026-07-20T14:30:00"
  },
  {
    "id": 2,
    "produtoId": 5,
    "tipo": "SAIDA",
    "quantidade": 4,
    "dataMovimentacao": "2026-07-20T15:10:00"
  }
]
```

### Caso não existam movimentações

A API deve retornar `200 OK` com uma lista vazia:

```json
[]
```

---

# Resumo dos endpoints

| Método | URI | Operação | Status principal |
|---|---|---|---|
| `GET` | `/api/produtos` | Listar produtos | `200 OK` |
| `GET` | `/api/produtos/{id}` | Consultar produto por ID | `200 OK` |
| `POST` | `/api/produtos` | Cadastrar produto | `201 Created` |
| `PUT` | `/api/produtos/{id}` | Atualizar produto | `200 OK` |
| `DELETE` | `/api/produtos/{id}` | Remover produto | `204 No Content` |
| `POST` | `/api/produtos/{id}/entradas` | Registrar entrada | `201 Created` |
| `POST` | `/api/produtos/{id}/saidas` | Registrar saída | `201 Created` |
| `GET` | `/api/movimentacoes` | Listar movimentações | `200 OK` |

---

# Conceitos usados

- **Entidade:** objeto do domínio representado no sistema, como um produto ou uma movimentação.
- **Recurso:** entidade ou conjunto de entidades disponibilizado pela API.
- **URI:** endereço usado para identificar um recurso.
- **Método HTTP:** informa a intenção da requisição, como consultar, cadastrar, atualizar ou remover.
- **Endpoint:** combinação entre método HTTP e URI.
- **JSON:** formato de texto usado para enviar e receber dados.
- **Código de status:** informa o resultado da requisição.
