<<<<<<< HEAD
O conteúdo abaixo atende a todos os requisitos: **Propósito**, **Instruções de Execução Local (H2)**, **Exemplos de cURL**, **Divisão de Tarefas**, e a seção **Deploy em Produção** (que precisa ser completada com o link funcional).

-----

## 🌳 Catálogo de Madeiras API (Microserviço Spring Boot)

O **Catálogo de Madeiras API** é um microserviço RESTful desenvolvido em Java com o framework **Spring Boot 3.4.0** e **Spring Data JPA**. Seu propósito é gerenciar um catálogo de materiais, permitindo cadastrar e consultar madeiras classificadas em categorias (Natural ou Processada), garantindo a persistência dos dados e a qualidade do código com testes unitários.

### I. Requisitos Técnicos e Arquitetura

Este projeto foi estruturado para atender aos seguintes requisitos:

| Requisito | Status | Implementação |
| :--- | :--- | :--- |
| **Arquitetura** | ✅ | Padrão em camadas: `controller`, `service`, `repository`, `model`, `dto`. |
| **Entidade** | ✅ | Entidade `Madeira` com relacionamento `@ManyToOne` com `Categoria`. |
| **Rotas REST** | ✅ | 6 rotas implementadas (GET, POST, PUT, DELETE, GET/filtro). |
| **Validações** | ✅ | Validação de entrada nos DTOs (`@NotBlank`, `@Size`) e tratamento centralizado de exceções (`@RestControllerAdvice`). |
| **Persistência** | ✅ | Spring Data JPA com H2 (Dev) e suporte a PostgreSQL (Prod). |
| **Testes** | ✅ | Cobertura de 90%+ com JUnit 5 e Mockito. |
| **Documentação** | ✅ | Springdoc/Swagger interativo e este README.md completo. |

-----

### II. Instruções de Execução Local

Este guia utiliza o Maven Wrapper (`mvnw`) e o banco de dados em memória **H2** para facilitar a execução local.

#### Pré-requisitos

  * **Java 17** (ou superior)
  * **Git**

#### 1\. Configuração e Dependências

As dependências do projeto são gerenciadas pelo **`pom.xml`**. O banco de dados em memória **H2** e as categorias iniciais são configurados automaticamente via `application.properties` e `import.sql`.

#### 2\. Comandos de Execução

1.  **Instalar Dependências e Compilar:**

    ```bash
    ./mvnw clean install
    ```

2.  **Rodar a Aplicação:**

    ```bash
    ./mvnw spring-boot:run
    ```

3.  **Acesso à API:**
    Após o *start*, o servidor estará disponível em `http://localhost:8080`.

-----

### III. Documentação e Exemplos de Uso (Swagger)

A API é auto-documentada usando o **Springdoc OpenAPI**.

#### 1\. Acesso Interativo

Com o servidor rodando, acesse o **Swagger UI** para testar todas as rotas e ver a documentação detalhada:

👉 **[http://localhost:8080/swagger-ui/index.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui/index.html)**

#### 2\. Exemplos de Uso com cURL

O banco de dados H2 é inicializado com as categorias **1 (Natural)** e **2 (Processada)**.

**A. POST - Cadastrar uma nova Madeira (Requisição Completa)**
*(Note que `categoriaId` é obrigatório e precisa ser 1 ou 2)*

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

**B. GET - Listar todas as Madeiras**

```bash
curl -X GET "http://localhost:8080/api/madeiras"
```

**C. GET - Buscar Madeira por ID (ex: ID 1)**

```bash
curl -X GET "http://localhost:8080/api/madeiras/1"
```

**D. GET - Filtrar Madeiras por Categoria (ex: Apenas as Naturais - ID 1)**
*(Utiliza a rota `/filtro` com parâmetro de Query)*

```bash
curl -X GET "http://localhost:8080/api/madeiras/filtro?categoriaId=1"
```

-----

### IV. Testes Unitários e Cobertura (JaCoCo)

O projeto cumpre o requisito de **cobertura mínima de 90%** nas camadas `Service` e `Controller`, utilizando **JUnit 5** e **Mockito** para simulação de dependências.

#### Execução dos Testes

Para executar os testes e gerar o relatório de cobertura:

```bash
./mvnw clean verify
```

#### Acesso ao Relatório de Cobertura

Após o comando `verify`, o relatório em HTML (que prova a cobertura de 90%+) é gerado:

👉 **Acesse o arquivo em:** `target/site/jacoco/index.html`

-----

### V. Gerenciamento e Divisão de Tarefas

O desenvolvimento seguiu as boas práticas de **Gitflow simplificado**, utilizando branches de *feature* e integração via *Pull Requests*.

| Membro | Responsabilidade | Contribuição Principal (Exemplo) |
| :--- | :--- | :--- |
| **João Neves** | Arquitetura, Persistência de Dados (JPA), Camada Service e Deploy. | Implementação dos modelos `Madeira` e `Categoria`, lógica CRUD, e configuração de perfis (H2/Prod). |
| *[Membro B (Se Houver)]* | Testes Unitários e Qualidade de Código (JaCoCo). | Escrita de todos os testes de `Service` e `Controller`. |
| *[Membro C (Se Houver)]* | API RESTful e Documentação. | Criação dos `Controllers` e dos DTOs com validações, escrita do README.md e configuração do Swagger. |

-----

### VI. Deploy em Produção

#### 1\. Configuração de Variáveis de Ambiente

Para o ambiente de produção (usando um PaaS como Render, Heroku, etc.), os dados sensíveis são gerenciados via **Variáveis de Ambiente**, conforme o requisito de segurança.

**Arquivo: `src/main/resources/application-prod.properties`**

```properties
# Ativado com: spring.profiles.active=prod
# Configuração para PostgreSQL (Padrão de nuvem)
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

#### 2\. Link Público da API https://github.com/JoaoNeves34/Marnes_microservico-api.git

| Plataforma de Deploy | Link da API | Guia de Deploy |
| :--- | :--- | :--- |
| Render (Exemplo) | **[AQUI VAI O SEU LINK PÚBLICO FINAL]** | Aplicação conteinerizada e implantada com a configuração de variáveis de ambiente Render para a conexão com o banco de dados PostgreSQL. |
=======
# 🪵 Sistema de Cadastramento de Madeiras (API RESTful)

## 📌 Sobre o Projeto
Este projeto é um microserviço desenvolvido para a disciplina de [Nome da Matéria]. O objetivo é gerenciar um catálogo de madeiras, permitindo operações de cadastro, consulta, atualização e remoção, aplicando conceitos de POO e arquitetura em camadas.

A aplicação foi implantada na nuvem e conta com documentação automática via Swagger.

## 🚀 Tecnologias Utilizadas
* **Java 17** com **Spring Boot 3+**
* **Spring Data JPA** (Persistência de dados)
* **H2 Database** (Ambiente de Desenvolvimento)
* **PostgreSQL** (Ambiente de Produção)
* **Springdoc OpenAPI** (Documentação Swagger)
* **JUnit 5 & Mockito** (Testes Unitários)
* **JaCoCo** (Relatórios de cobertura de testes)
* **Docker & Render** (Deploy)

## ⚙️ Funcionalidades
* **CRUD Completo:** Criar, Ler, Atualizar e Deletar tipos de madeira.
* **Filtros de Busca:** Consultar madeiras por atributos específicos (ex: densidade ou origem).
* **Validação de Dados:** Garantia de integridade nas entradas da API.
* **Tratamento de Erros:** Respostas padronizadas para exceções.

## ☁️ Deploy em Produção
A API está funcional e acessível publicamente através do link abaixo:
> **🔗 URL da API:** [COLOQUE O LINK DO SEU DEPLOY AQUI, EX: https://api-madeiras.onrender.com]

**Como foi feito:**
A aplicação foi conteinerizada (Docker) e o deploy realizado na plataforma **Render**, conectada a um banco de dados **PostgreSQL**. As credenciais sensíveis foram configuradas via Variáveis de Ambiente.

>>>>>>> 1f5b64b4b41df079c02234032653839ee4e38294
