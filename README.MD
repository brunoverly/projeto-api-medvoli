<div align="center">

# 🏥 MedVoli API
### API completa para front-end de uma clínica médica

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

</div>

---

## ✨ Visão geral

A **MedVoli API** é uma API REST desenvolvida em **Java + Spring Boot** para servir como back-end de um sistema de **clínica médica**, incluindo:

- ✅ **CRUD** e validações com Bean Validation
- 🗄️ Persistência com **Spring Data JPA** + **PostgreSQL**
- 🧬 **Migrações de banco** com **Flyway**
- 🔐 **Autenticação/Autorização** com **Spring Security + JWT**
- 📚 Documentação interativa com **Swagger / OpenAPI (springdoc)**

---

## 🧰 Tecnologias (Stack)

| Camada | Tecnologias |
|-------:|-------------|
| ☕ Linguagem | **Java 17** |
| 🌱 Framework | **Spring Boot 3.4.3** |
| 🌐 Web | Spring Web (REST) |
| ✅ Validação | Spring Validation |
| 🗃️ Persistência | Spring Data JPA |
| 🐘 Banco de dados | PostgreSQL |
| 🧬 Migrations | Flyway |
| 🔐 Security | Spring Security |
| 🎫 Tokens | JWT (Auth0 `java-jwt`) |
| 📖 Docs | springdoc OpenAPI + Swagger UI |

---

## ✅ Requisitos

- ☕ **Java 17+**
- 🐘 **PostgreSQL**
- 🧱 Maven (ou use o wrapper `mvnw` / `mvnw.cmd`)

---

## 🧬 Migrações (Flyway)

📁 Local das migrations:

- `src/main/resources/db/migration`

📌 Migrations existentes:

- `V1__CREATE_TABLE_MEDICOS.sql`
- `V2__CREATE_TABLE_USUARIOS.sql`
- `V3__ALTER_TABLE_USUARIOS.sql`
- `V4__CREATE_TABLE_PACIENTES.sql`
- `V5__CREATE_TABLE_CONSULTAS.sql`

✅ Ao iniciar a aplicação, o Flyway executa automaticamente as migrations (habilitado e configurado para `classpath:db/migration`).

---

## ▶️ Como executar

### 🚀 Subir a aplicação (Maven Wrapper)

**Linux/macOS:**
```bash
./mvnw spring-boot:run
```

**Windows:**
```bat
mvnw.cmd spring-boot:run
```

### 🧪 Rodar testes

```bash
./mvnw test
```

---

## 📚 Swagger / OpenAPI

A API inclui documentação interativa com Swagger UI (springdoc).

Após iniciar o projeto, acesse:

- 🔗 `/swagger-ui.html`  
  *(ou `/swagger-ui/index.html`, dependendo da versão/configuração)*

---

## 🔐 Autenticação (JWT)

A API utiliza:

- **Spring Security**
- **Auth0 `java-jwt`**

📌 Para chamadas autenticadas, use o header:

- `Authorization: Bearer <token>`

> Sugestão: quando você quiser, pode adicionar aqui exemplos de endpoints de login, payloads e regras de acesso (público x autenticado).

---

## 🗂️ Estrutura (alto nível)

```
src/
 ├─ main/
 │  ├─ java/medVoli/         # código-fonte
 │  └─ resources/
 │     ├─ application.properties
 │     └─ db/migration/      # migrations Flyway
 └─ test/                    # testes
pom.xml                      # dependências e build
```

---

## ℹ️ Sobre o repositório

- 📦 **Repo:** `brunoverly/projeto-api-medvoli`
- 📝 **Descrição:** Projeto de uma API completa para um front end de um projeto para clinica médica.

---