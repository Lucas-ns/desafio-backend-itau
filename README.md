# 🚀 Desafio Itaú - Backend

Este projeto é uma implementação do desafio técnico para desenvolvedores backend (Itaú), focado em performance e integridade de dados. A aplicação é uma API REST que recebe transações financeiras e calcula estatísticas em tempo real baseadas nos últimos 60 segundos.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4+** (Configurado no projeto como 4.0.5)
- **Maven**
- **Lombok**
- **JUnit 5 / Mockito** (Para testes unitários e de integração)
- **Jakarta Validation** (Para validação de dados)

---

## 📋 Funcionalidades

### 1. Receber Transações
`POST /transacao`

Recebe uma transação e a armazena em memória para fins de estatística.

**Corpo da Requisição (JSON):**
```json
{
    "valor": 123.45,
    "dataHora": "2020-08-07T12:34:56.789-03:00"
}
```

**Regras de Negócio:**
- O valor deve ser maior ou igual a zero (validação `@Positive` aplicada).
- A data não pode estar no futuro (validação `@PastOrPresent` aplicada).
- Transações com mais de 60 segundos não são consideradas para estatística, mas são aceitas pela API (conforme requisitos padrão do desafio).

### 2. Limpar Transações
`DELETE /transacao`

Remove todas as transações armazenadas na memória.

### 3. Estatísticas
`GET /estatistica`

Calcula as estatísticas das transações que ocorreram nos últimos 60 segundos.

**Resposta Exemplo:**
```json
{
    "count": 10,
    "sum": 1234.50,
    "avg": 123.45,
    "min": 10.00,
    "max": 500.00
}
```

---

## 🏗️ Estrutura do Projeto

A aplicação segue uma arquitetura modular por funcionalidades:

- `dev.java10x.desafio_itau_backend.transacao`: Gerencia o ciclo de vida das transações.
- `dev.java10x.desafio_itau_backend.estatistica`: Responsável pelo processamento estatístico em tempo real.
- `dev.java10x.desafio_itau_backend.exceptions`: Centralizador de tratamento de erros da API.

---

## 🚀 Como Executar

### Pré-requisitos
- JDK 17
- Maven 3.8+

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/desafio-itau-backend.git
    ```
2.  **Acesse o diretório:**
    ```bash
    cd desafio-itau-backend
    ```
3.  **Compile o projeto:**
    ```bash
    mvn clean install
    ```
4.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```

A API estará disponível em `http://localhost:8080`.

---

## 🧪 Testes

O projeto conta com cobertura de testes unitários para Services e Controllers, garantindo que as regras de negócio e os contratos da API sejam respeitados.

Para rodar os testes:
```bash
mvn test
```

---

## 👨‍💻 Desenvolvido por

[Lucas do Nascimento](https://github.com/Dimensional-LucasNas)
