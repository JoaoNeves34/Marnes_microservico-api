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
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
server.port=${PORT:8080}
```

### 2. Deploy e Link Público da API

---

## 🚀 **Guia de Deploy em Produção**

### **Opção 1: Deploy no Render (Recomendado - GRÁTIS)**

1. **Crie uma conta em [render.com](https://render.com)**

2. **Conecte seu repositório GitHub:**
   - Dashboard → New → Blueprint
   - Selecione o repositório: `JoaoNeves34/Marnes_microservico-api`
   - O Render detectará automaticamente o arquivo `render.yaml`

3. **Configuração automática:**
   - ✅ Web Service criado automaticamente
   - ✅ PostgreSQL database criado
   - ✅ Variáveis de ambiente configuradas
   - ✅ Build e deploy iniciados

4. **Aguarde o build (3-5 minutos)**

5. **Acesse sua API:**
   ```
   https://marnes-microservico-api.onrender.com/api/madeiras
   https://marnes-microservico-api.onrender.com/swagger-ui/index.html
   ```

---

### **Opção 2: Deploy no Railway**

1. **Acesse [railway.app](https://railway.app) e faça login com GitHub**

2. **New Project → Deploy from GitHub repo**

3. **Selecione o repositório e configure:**
   ```bash
   Build Command: ./mvnw clean package -DskipTests
   Start Command: java -Dserver.port=$PORT -Dspring.profiles.active=prod -jar target/*.jar
   ```

4. **Adicione PostgreSQL:**
   - New → Database → PostgreSQL
   - Railway criará automaticamente `DATABASE_URL`

5. **Defina variáveis de ambiente:**
   ```
   SPRING_PROFILES_ACTIVE=prod
   PORT=8080
   ```

6. **Deploy automático ativo!**

---

### **Opção 3: Deploy no Heroku**

1. **Instale Heroku CLI e faça login:**
   ```bash
   heroku login
   ```

2. **Crie aplicação e adicione PostgreSQL:**
   ```bash
   heroku create marnes-api
   heroku addons:create heroku-postgresql:mini
   ```

3. **Configure variáveis:**
   ```bash
   heroku config:set SPRING_PROFILES_ACTIVE=prod
   ```

4. **Deploy:**
   ```bash
   git push heroku main
   ```

5. **Acesse:**
   ```bash
   heroku open
   ```

---

### **Opção 4: Docker Local**

1. **Build da imagem:**
   ```bash
   docker build -t marnes-api .
   ```

2. **Executar container:**
   ```bash
   docker run -p 8080:8080 \
     -e SPRING_PROFILES_ACTIVE=prod \
     -e DATABASE_URL=jdbc:postgresql://host.docker.internal:5432/madeiradb \
     -e DB_USER=postgres \
     -e DB_PASSWORD=postgres \
     marnes-api
   ```

3. **Acesse:** `http://localhost:8080`

---

## 📊 **Variáveis de Ambiente Necessárias**

| Variável                | Descrição                        | Exemplo                                          |
| :---------------------- | :------------------------------- | :----------------------------------------------- |
| `SPRING_PROFILES_ACTIVE` | Perfil ativo (prod/dev)          | `prod`                                           |
| `DATABASE_URL`          | URL do banco PostgreSQL          | `jdbc:postgresql://host:5432/madeiradb`          |
| `DB_USER`               | Usuário do banco                 | `postgres`                                       |
| `DB_PASSWORD`           | Senha do banco                   | `sua-senha-segura`                               |
| `PORT`                  | Porta do servidor (opcional)     | `8080`                                           |

---

## 🧪 **Testando a API em Produção**

Após deploy, teste os endpoints:

```bash
# Listar categorias
curl https://sua-api.onrender.com/api/categorias

# Criar madeira
curl -X POST https://sua-api.onrender.com/api/madeiras \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Ipê",
    "origem": "Brasil",
    "densidade": "1050 kg/m³",
    "resistencia": "Alta",
    "cor": "Marrom",
    "categoriaId": 1
  }'

# Swagger UI
https://sua-api.onrender.com/swagger-ui/index.html
```

---

# VII. Tecnologias Utilizadas

* **Backend:** Java 17, Spring Boot 3.4.0, Spring Data JPA
* **Banco de Dados:** H2 (desenvolvimento), PostgreSQL (produção)
* **Documentação:** Springdoc OpenAPI 2.6.0 (Swagger)
* **Testes:** JUnit 5, Mockito, JaCoCo (90%+ cobertura)
* **Containerização:** Docker multi-stage build
* **Deploy:** Render, Railway, Heroku (PaaS)
* **Versionamento:** Git, GitHub

---

# 🎉 Projeto Completo e Pronto para Produção!

