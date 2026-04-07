# Projeto Usuário

## Descrição

Este projeto é uma aplicação Spring Boot desenvolvida para gerenciar informações de usuários. Ele utiliza Java 21 e Gradle para construção, integrando-se com um banco de dados PostgreSQL via Docker Compose. A aplicação incorpora funcionalidades de segurança com Spring Security, persistência de dados com Spring Data JPA e comunicação entre serviços com OpenFeign.

## Tecnologias Utilizadas

As principais tecnologias e ferramentas utilizadas neste projeto incluem:

*   **Java 21**: Linguagem de programação principal.
*   **Spring Boot 3.2.5**: Framework para construção de aplicações Java robustas e eficientes.
*   **Gradle**: Ferramenta de automação de build.
*   **Spring Data JPA**: Para persistência de dados e interação com o banco de dados.
*   **Spring Security**: Para autenticação e autorização.
*   **Spring Boot Starter Web**: Para a construção de aplicações web e APIs RESTful.
*   **Spring Cloud Starter OpenFeign**: Para comunicação declarativa entre serviços.
*   **Lombok**: Para reduzir o código boilerplate.
*   **MySQL Connector/J**: Driver JDBC para conexão com MySQL (embora o docker-compose use PostgreSQL, o build.gradle lista MySQL Connector).
*   **JUnit 5**: Para testes unitários e de integração.
*   **SonarQube**: Para análise de qualidade de código.
*   **Docker**: Para conteinerização da aplicação.
*   **Docker Compose**: Para orquestração de serviços (aplicação e banco de dados PostgreSQL).

## Estrutura do Projeto

A estrutura do projeto segue as convenções de um projeto Spring Boot, com pacotes organizados por funcionalidade:

```
src/
├── main/
│   ├── java/
│   │   └── com/costadev/usuario/
│   │       ├── business/             # Lógica de negócio e serviços
│   │       ├── controller/           # Controladores REST
│   │       └── infrasctruture/       # Configurações de infraestrutura e exceções
│   └── resources/            # Arquivos de configuração e templates
├── test/
│   └── java/
│       └── com/costadev/usuario/     # Testes unitários e de integração
└── ...
```

## Como Executar

Para executar a aplicação localmente, siga os passos abaixo:

1.  **Pré-requisitos**:
    *   Java Development Kit (JDK) 21 ou superior.
    *   Docker e Docker Compose instalados.

2.  **Clonar o repositório**:

    ```bash
    git clone https://github.com/guilhermeoliveira-software/usuario.git
    cd usuario
    ```

3.  **Configurar e iniciar com Docker Compose**:

    ```bash
    docker-compose up --build
    ```

    Isso irá construir a imagem da aplicação, iniciar o contêiner da aplicação e o banco de dados PostgreSQL. A aplicação estará disponível em `http://localhost:8080`.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests no repositório.

