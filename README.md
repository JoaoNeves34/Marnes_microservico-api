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

## 🔧 Como Executar Localmente

### Pré-requisitos
* Java JDK 17
* Maven
* Git
