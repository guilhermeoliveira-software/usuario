# 👤 Gestão de Usuários

Microserviço responsável pelo gerenciamento de perfis de usuários, autenticação via **JWT** e integração com a **API ViaCEP** para validação de endereços.

🔗 **API em produção:** [usuario-production-8686.up.railway.app/swagger-ui.html](https://usuario-production-8686.up.railway.app/swagger-ui.html)

---

## 📌 Sobre o Projeto

Este serviço é parte de um ecossistema de microserviços para agendamento de tarefas. Ele é responsável por toda a camada de identidade e acesso, garantindo que apenas usuários autenticados possam interagir com os demais serviços.

| Serviço | Responsabilidade |
|---|---|
| [BFF Agendador](https://github.com/guilhermeoliveira-software/bff-agendador-tarefas) | Orquestração e gateway para o frontend |
| **Gestão de Usuários** (este) | Autenticação e gerenciamento de perfis |
| [Agendador de Tarefas](https://github.com/guilhermeoliveira-software/agendador-tarefas) | Ciclo de vida e agendamento das tarefas |
| [Notificação por E-mail](https://github.com/guilhermeoliveira-software/notificacao) | Envio de e-mails e lembretes |

---

## 🚀 Funcionalidades

- Cadastro e atualização de perfis de usuários
- Autenticação com **JWT** (tokens de curta duração)
- Autorização com **Spring Security**
- Integração com **API ViaCEP** para preenchimento automático de endereço
- **Global Exception Handler** para padronização de erros
- Documentação interativa via **Swagger**

---

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 3.2.5**
- **Spring Security + JWT**
- **Spring Data JPA / Hibernate**
- **PostgreSQL**
- **Spring Cloud OpenFeign**
- **Docker**
- **CI/CD com GitHub Actions**
- **SonarQube**
- **Swagger / SpringDoc OpenAPI**
- **Gradle**

---

## 📁 Estrutura do Projeto

```
src/
└── main/
    └── java/
        └── com/COSTADev/usuario/
            ├── business/          # Lógica de negócio e serviços
            ├── controller/        # Endpoints REST
            └── infrastructure/    # Configurações de segurança, JPA e exceções
```

---

## ⚙️ Como Executar Localmente

### Pré-requisitos
- Java 21+
- Docker e Docker Compose

### Passos

```bash
# Clone o repositório
git clone https://github.com/guilhermeoliveira-software/usuario.git
cd usuario

# Configure as variáveis de ambiente
# Edite o application.properties com suas credenciais locais

# Suba com Docker Compose
docker-compose up --build
```

A API estará disponível em: `http://localhost:8080`
Documentação Swagger: `http://localhost:8080/swagger-ui.html`

---

## 🌍 Variáveis de Ambiente

| Variável | Descrição |
|---|---|
| `SPRING_DATASOURCE_URL` | URL de conexão com o PostgreSQL |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco de dados |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco de dados |
| `VIACEP_URL` | URL da API ViaCEP |

---

## 🔐 Segurança

A autenticação é feita via **JWT**. Para acessar endpoints protegidos:

1. Faça login via `POST /auth/login`
2. Copie o token retornado
3. Adicione no header: `Authorization: Bearer {token}`

---

## 👨‍💻 Autor

**José Guilherme Da Costa Oliveira**
- 💼 [LinkedIn](https://www.linkedin.com/in/guilherme-costa-oliveiraa/)
- 🐙 [GitHub](https://github.com/guilhermeoliveira-software)
-
