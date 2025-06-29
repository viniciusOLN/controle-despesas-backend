# Controle de Despesas - Backend + MySQL com Docker

Este projeto contém uma aplicação backend (Spring Boot) conectada a um banco MySQL, utilizando Docker e Docker Compose.

---

## ✅ Pré-requisitos

- [Docker](https://www.docker.com/)
- [Java 17](https://www.java.com/pt-BR/)

---

## ▶️ Rodando o projeto com Docker

Para rodar a aplicação com docker é necessário primeiramente ter o docker instalado. Após instalação e configuração basta rodar o seguinte comando:
 - docker compose up -build

A aplicação está configurada para ouvir o banco Mysql pela porta 3307, logo é necesário confirmar que nada está rodando na porta referida.

Para rodar a aplicação basta usar alguma IDE de preferencia que suporte rodar aplicações SpringBoot, sendo possível também a opção por linha de comando. O único diferencial para a versão do docker é que o banco foi configurado na porta 3306, padrão do mysql.

Em ambas as versões a aplicação está rodando na porta 8080.

## ▶️ Documentação

A documentação dos endpoints foi feita através do swagger. A mesma pode ser acessada após rodar a aplicação através do seguinte link:
 - [swagger](http://localhost:8080/swagger-ui/index.html?url=v3/api-docs)