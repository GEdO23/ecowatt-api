# EcoWatt API
### Este projeto se trata de uma REST API em Springboot, criada para o Desafio Global Solution da FIAP.

# Endpoints

#### Importante: Todo endpoint exceto os de autorização e cadastro de usuários são necessários utilizar o header de authorization com Bearer Token. EX:

```
  Authorization: Bearer {token}
```

## User

### POST */api/user/signup*

Este endpoint tem a função de cadastrar um usuário novo no banco de dados.

**Corpo da requisição:**

```
{
  "nome" : "Marcelo Dias",
  "email" : "marcelo@teste.com",
  "senha" : "senha123"    
}
```

**Resposta:**

```
{
  "id" : "4e2ff3d4-a81f-43bd-bdaa-65f2a5f3fcd7",
  "nome" : "Marcelo Dias",
  "email" : "marcelo@teste.com"
}
```

---

### POST */api/user/auth*

Este endpoint tem a função de autenticar um usuário.

**Corpo da requisição:**

```
{
  "email" : "marcelo@teste.com",
  "password" : "senha123"    
}
```

**Resposta:**

```
{
  "id" : "4e2ff3d4-a81f-43bd-bdaa-65f2a5f3fcd7",
  "nome" : "Marcelo Dias",
  "email" : "marcelo@teste.com",
  "token" : "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
    .eyJlbWFpbCI6Im1hcmNlbG9AdGVzdGUuY29tIiwicGFzc3
    dvcmQiOiJzZW5oYTEyMyIsImlhdCI6MTUxNjIzOTAyMn0.
    tLUK67H_YJKWULYHPPBUvHBPnU_DnYkBcRvv1GkiSrQ"
}
```

---

### GET */api/user/*

Este endpoint tem a função de buscar todos os usuários cadastrados no banco.

**Resposta:**

```
[
  {
    "id" : "4e2ff3d4-a81f-43bd-bdaa-65f2a5f3fcd7",
    "nome" : "Marcelo Dias",
    "email" : "marcelo@teste.com"
  },
  {
    "id" : "44b76fd8-c736-4887-bff9-47a414c37c80",
    "nome" : "Gabriel",
    "email" : "gabriel@teste.com"
  }
]
```

---

### GET */api/user/{id}*

Este endpoint tem a função de buscar um usuário específico pelo ID.

**Resposta:**

```
{
  "id" : "4e2ff3d4-a81f-43bd-bdaa-65f2a5f3fcd7",
  "nome" : "Marcelo Dias",
  "email" : "marcelo@teste.com"
}
```

---

### PUT */api/user/update*

Este endpoint tem a função de atualizar dados de um usuário no banco de dados.

**Corpo da requisição:**

```
{
  "id" : "4e2ff3d4-a81f-43bd-bdaa-65f2a5f3fcd7",
  "nome" : "Dado atualizado",
  "email" : "marcelo@teste.com",
  "senha" : "senha123"    
}
```

**Resposta:**

```
{
  "id" : "4e2ff3d4-a81f-43bd-bdaa-65f2a5f3fcd7",
  "nome" : "Dado atualizado",
  "email" : "marcelo@teste.com"
}
```

---

### DELETE */api/user/delete/{id}*

Este endpoint tem a função de deletar um usuário do banco de dados.

**Resposta:**

```
  204: no content
```

## Dispositivo

### POST */api/dispositivo*

Este endpoint tem a função de cadastrar um novo dispositivo no banco de dados.

**Corpo da requisição:**

```
{
  "nome" : "Impressora",
  "local" : "Quarto 2",
  "tipo" : "Eletrônicos",
  "limiteConsumo" : 350.00 
}
```

**Resposta:**

```
{
  "id" : "25965e1a-b5f8-4dd2-9c9f-29bb0a5b4319",
  "nome" : "Impressora",
  "local" : "Quarto 2",
  "tipo" : "Eletrônicos",
  "limiteConsumo" : 350.00,
  "consumos" : [],
  "alertas" : []
}
```

---

### GET */api/dispositivo*

Este endpoint tem a função de buscar todos dispositivos no banco de dados.

**Resposta:**

```
[
  {
    "id" : "25965e1a-b5f8-4dd2-9c9f-29bb0a5b4319",
    "nome" : "Impressora",
    "local" : "Quarto 2",
    "tipo" : "Eletrônicos",
    "limiteConsumo" : 350.00
  },
  {
    "id" : "25965e1a-b5f8-4dd2-9c9f-29bb0a5b4319",
    "nome" : "Impressora",
    "local" : "Quarto 2",
    "tipo" : "Eletrônicos",
    "limiteConsumo" : 350.00
  }
]
```

---

### GET */api/dispositivo/{id}*

Este endpoint tem a função de buscar um dispositivo específico pelo ID no banco de dados.

**Resposta:**

```
{
  "id" : "25965e1a-b5f8-4dd2-9c9f-29bb0a5b4319",
  "nome" : "Impressora",
  "local" : "Quarto 2",
  "tipo" : "Eletrônicos",
  "limiteConsumo" : 350.00,
  "consumos" : [],
  "alertas" : []
}
```

---

### PUT */api/dispositivo/*

Este endpoint tem a função de atualizar dados de um dispositivo no banco de dados.

**Corpo da Requisição:**

```
{
  "id" : "25965e1a-b5f8-4dd2-9c9f-29bb0a5b4319",
  "nome" : "Impressora Atualizada",
  "local" : "Quarto 2",
  "tipo" : "Eletrônicos",
  "limiteConsumo" : 400.00
}
```

**Resposta:**

```
{
  "id" : "25965e1a-b5f8-4dd2-9c9f-29bb0a5b4319",
  "nome" : "Impressora Atualizada",
  "local" : "Quarto 2",
  "tipo" : "Eletrônicos",
  "limiteConsumo" : 400.00,
  "consumos" : [],
  "alertas" : []
}
```

---

### DELETE */api/dispositivo/{id}*

Este endpoint tem a função de deletar um dispositivo no banco de dados.

**Resposta:**

```
  204: no content
```

---

## Consumo

### POST */api/consumo*

Este endpoint tem a função de registrar um novo consumo no banco de dados.

**Corpo da requisição:**

```
{
  "consumo" : 100.00,
  "idDispositivo" : "25965e1a-b5f8-4dd2-9c9f-29bb0a5b4319"
}
```

**Resposta:**

```
{
  "id" : "a31e19d9-8b3c-4190-8730-dd5aef6a71c2",
  "consumo" : 100.00,
  "dataHora" : "2024-11-13T08:42:32.455",
  "idDispositivo" : "25965e1a-b5f8-4dd2-9c9f-29bb0a5b4319"
}
```

---

### GET */api/consumo/dispositivo/{id}*

Este endpoint tem a função de buscar todos os consumos de um dispositivo específico no banco de dados.

**Resposta:**

```
[
  {
    "id" : "a31e19d9-8b3c-4190-8730-dd5aef6a71c2",
    "consumo" : 100.00,
    "dataHora" : "2024-11-13T08:42:32.455",
    "idDispositivo" : "25965e1a-b5f8-4dd2-9c9f-29bb0a5b4319"
  },
  {
    "id" : "75f4df2c-f919-4153-ad89-b5e4f9d31039",
    "consumo" : 200.00,
    "dataHora" : "2024-11-13T09:02:12.205",
    "idDispositivo" : "25965e1a-b5f8-4dd2-9c9f-29bb0a5b4319"
  }
]
```

---

### GET */api/consumo/relatorio?data=YYYY-MM-DD*

Este endpoint tem a função de gerar um relatório de consumo de todos os dispositivos de um dia específico.

**Resposta:**

```
{
  "totalConsumo" : 300.00,
  "mensagem" : "O consumo do dia 2024-13-11 foi de 300W.",
  "data" : "2024-13-11"
}
```

---

## Alertas

### GET */api/alertas/dispositivo/{id}*

Este endpoint tem a função de buscar todos os consumos de um dispositivo específico no banco de dados.

**Resposta:**

```
[
  {
    "id" : "44fd44d2-16f6-45f8-ad39-16b637b5c8aa",
    "mensagem" : "O Limite de 350W do dispositivo Impressora foi excedido: 400W",
    "dataHora" : "2024-11-13T09:02:12.205",
    "idDispositivo" : "25965e1a-b5f8-4dd2-9c9f-29bb0a5b4319"
  },
  {
    "id" : "72ac38f1-02aa-4193-98af-68d26c4adff6",
    "mensagem" : "O Limite de 350W do dispositivo Impressora foi excedido: 365W",
    "dataHora" : "2024-11-13T10:02:12.205",
    "idDispositivo" : "25965e1a-b5f8-4dd2-9c9f-29bb0a5b4319"
  },
]
```
