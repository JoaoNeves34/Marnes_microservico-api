

# ✅ **README.md — Projeto Microserviço de Madeiras**

```markdown
# 🌲 Microserviço de Madeiras — API REST com Spring Boot

API desenvolvida para gerenciar madeiras e categorias, permitindo cadastro, consulta, atualização e exclusão de registros.  
O objetivo é fornecer uma base sólida para estudos de **Java + Spring Boot + JPA + H2/PostgreSQL**, incluindo testes, documentação Swagger e um fluxo de deploy funcional.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.0**
- **Spring Web**
- **Spring Data JPA**
- **Spring Validation**
- **H2 Database** (ambiente local)
- **PostgreSQL** (produção / deploy)
- **Springdoc OpenAPI 2.6.0 (Swagger UI)**
- **Maven**
- **Jacoco** (cobertura de testes)
- **Docker** (opcional p/ deploy)
- **Render.com ou Heroku** (guia de deploy incluído)

---

## 📁 Estrutura do Projeto

```

src/main/java
└── br/com/joaoneves/marnes/microservico_api
├── controller         # Controllers REST
├── dto                # Data Transfer Objects
├── infra              # Handler Global de Exceções
├── model              # Entidades JPA
├── repository         # Interfaces Repository
└── service            # Regras de negócio

````

---

## ⚙️ Como Rodar o Projeto Localmente

### 1️⃣ Pré-requisitos
- Java 17 instalado
- Maven Wrapper (já incluso no projeto)
- Git instalado

### 2️⃣ Clonar o repositório

```bash
git clone https://github.com/seu-usuario/seu-repo.git
cd seu-repo
````

### 3️⃣ Executar a aplicação

```bash
.\mvnw clean install -DskipTests
.\mvnw spring-boot:run
```

### 4️⃣ Acessar Swagger

* **Swagger UI:**
  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

* **OpenAPI JSON:**
  [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🗄️ Banco de Dados

### 🔹 Ambiente Local

Usa **H2 em memória**.
Console acessível em:

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

Credenciais (padrão):

```
JDBC URL: jdbc:h2:mem:madeiradb
User: SA
Password: (em branco)
```

### 🔹 Ambiente de Produção

Usa **PostgreSQL**, com configuração opcional no arquivo:

```
src/main/resources/application-mysql.properties
```

---

## 📌 Endpoints Principais

### 📁 Categorias

| Verbo  | Rota             | Descrição           |
| ------ | ---------------- | ------------------- |
| GET    | /categorias      | Lista categorias    |
| POST   | /categorias      | Cria nova categoria |
| GET    | /categorias/{id} | Busca por ID        |
| PUT    | /categorias/{id} | Atualiza categoria  |
| DELETE | /categorias/{id} | Remove              |

### 🌲 Madeiras

| Verbo  | Rota           | Descrição      |
| ------ | -------------- | -------------- |
| GET    | /madeiras      | Lista madeiras |
| POST   | /madeiras      | Cria madeira   |
| GET    | /madeiras/{id} | Busca por ID   |
| PUT    | /madeiras/{id} | Atualiza       |
| DELETE | /madeiras/{id} | Remove         |

---

# 🧪 Testes Automatizados

O projeto inclui testes unitários em:

```
src/test/java/...
```

Para rodar:

```bash
.\mvnw test
```

---

# ☁️ Deploy (Guia Completo)

Esta API pode ser implantada em Render, Heroku, Railway ou qualquer plataforma compatível com Java.
Abaixo segue o guia recomendado.

---

## 🚀 Deploy no Render (Recomendado)

### 1️⃣ Criar o arquivo **Dockerfile** na raiz do projeto

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/microservico-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
```

### 2️⃣ Gerar o .jar

```bash
.\mvnw clean package -DskipTests
```

O arquivo gerado estará em:

```
target/microservico-api-0.0.1-SNAPSHOT.jar
```

### 3️⃣ Criar novo Web Service no Render

* Tipo: **Docker**
* Build: Automático ou manual
* Porta interna: **8080**
* Banco: **PostgreSQL do Render**

### 4️⃣ Variáveis de ambiente no Render

```
SPRING_DATASOURCE_URL=<url_do_postgres>
SPRING_DATASOURCE_USERNAME=<usuario>
SPRING_DATASOURCE_PASSWORD=<senha>
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

---

## 🚀 Deploy no Heroku (Alternativa)

### 1️⃣ Criar arquivo **Procfile**

```
web: java -Dserver.port=$PORT -jar target/microservico-api-0.0.1-SNAPSHOT.jar
```

### 2️⃣ Subir para o Heroku

```bash
heroku create nome-da-sua-api
heroku addons:create heroku-postgresql:hobby-dev
git push heroku main
```

---

# 🔐 Variáveis de Ambiente Recomendadas

```
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=<Postgres URL>
```

---

# 🧹 Comandos Úteis

### Limpar cache Maven (Windows)

```powershell
rd /s /q "%USERPROFILE%\.m2\repository\org\springdoc"
```

### Forçar reconstrução total

```bash
.\mvnw clean install -U
```

---

# 👨‍💻 Autor

**João Neves**
Projeto desenvolvido para estudo/portfólio Full Stack.

---

# ✔️ Licença

Este projeto está sob a licença MIT.
