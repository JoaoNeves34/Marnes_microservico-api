---

# ✅ **README FINAL

---

## 🌳 Catálogo de Madeiras API (Microserviço Spring Boot)

O **Catálogo de Madeiras API** é um microserviço RESTful desenvolvido em Java com **Spring Boot 3.4.0**, **Spring Data JPA**, e arquitetura em camadas.
Seu propósito é gerenciar um catálogo de materiais, permitindo cadastrar e consultar madeiras classificadas em categorias (Natural ou Processada).
A aplicação foi implantada na nuvem e conta com documentação automática via Swagger.

---

# I. Requisitos Técnicos e Arquitetura

Este projeto foi estruturado para atender aos seguintes requisitos:

| Requisito        | Status | Implementação                                                       |
| :--------------- | :----- | :------------------------------------------------------------------ |
| **Arquitetura**  | ✅      | Camadas: `controller`, `service`, `repository`, `model`, `dto`.     |
| **Entidade**     | ✅      | Entidade `Madeira` com relacionamento `@ManyToOne` com `Categoria`. |
| **Rotas REST**   | ✅      | 6 rotas (GET, POST, PUT, DELETE e GET com filtro).                  |
| **Validações**   | ✅      | DTOs com annotations (`@NotBlank`, `@Size`) e exceções globais.     |
| **Persistência** | ✅      | Spring Data JPA com H2 (dev) e PostgreSQL (prod).                   |
| **Testes**       | ✅      | Cobertura 90%+ com JUnit 5 e Mockito.                               |
| **Documentação** | ✅      | Swagger/OpenAPI com interface interativa.                           |

---

# II. Instruções de Execução Local

### Pré-requisitos

* **Java 17+**
* **Git**

### 1. Instalar dependências e compilar:

```bash
./mvnw clean install
```

### 2. Rodar a aplicação:

```bash
./mvnw spring-boot:run
```

Servidor disponível em:

```
http://localhost:8080
```

---

# III. Documentação e Exemplos (Swagger)

### 📘 Acesso Interativo

Swagger UI:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

---

### 📡 Exemplos de cURL

A base H2 inicia com categorias:

* **1 – Natural**
* **2 – Processada**

#### A. Criar nova madeira:

```bash
curl -X POST "http://localhost:8080/api/madeiras" \
-H "Content-Type: application/json" \
-d '{
  "nome": "Ipê",
  "origem": "Amazônia",
  "densidade": "1050 kg/m³",
  "resistencia": "Alta a Cupins",
  "cor": "Marrom Escuro",
  "categoriaId": 1
}'
```

#### B. Listar todas:

```bash
curl -X GET "http://localhost:8080/api/madeiras"
```

#### C. Buscar por ID:

```bash
curl -X GET "http://localhost:8080/api/madeiras/1"
```

#### D. Filtrar por categoria (ex: Naturais — ID 1):

```bash
curl -X GET "http://localhost:8080/api/madeiras/filtro?categoriaId=1"
```

---

# IV. Testes Unitários e Cobertura (JaCoCo)

O projeto cumpre a cobertura mínima de **90%+** nas camadas de serviço e controller.

### Rodar testes:

```bash
./mvnw clean verify
```

### Relatório HTML:

Abra:

```
target/site/jacoco/index.html
```

---

# V. Gerenciamento e Divisão de Tarefas

O desenvolvimento utilizou um **Gitflow simplificado**, com branches de *feature*, *hotfix* e PRs.

| Membro         | Responsabilidade                           | Contribuição                            |
| :------------- | :----------------------------------------- | :-------------------------------------- |
| **João Neves** | Arquitetura, Service, Persistência, Deploy | JPA Models, lógica CRUD, perfis H2/Prod |
| *[Membro B]*   | Testes unitários                           | Testes `Service` e `Controller`         |
| *[Membro C]*   | Controllers e Documentação                 | DTOs, validação, Swagger, README        |

---

# VI. Deploy em Produção

### 1. Configuração das Variáveis de Ambiente (Prod)

Arquivo **`application-prod.properties`**:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

### 2. Deploy e Link Público da API

A aplicação foi conteinerizada com Docker e implantada no **Render**, com banco PostgreSQL e variáveis de ambiente seguras.

| Plataforma | Link da API                                    | Guia                     |
| :--------- | :--------------------------------------------- | :----------------------- |
| **Render** | **👉 COLOQUE AQUI O LINK FINAL DO SEU DEPLOY** | Deploy Docker + Postgres |

---

# VII. Tecnologias Utilizadas

* Java 17
* Spring Boot 3+
* Spring Data JPA
* H2 Database
* PostgreSQL
* Springdoc OpenAPI (Swagger)
* JUnit 5, Mockito, JaCoCo
* Docker
* Render (deploy cloud)

---

# 🎉 Pronto!

