# Controle de Despesas - Backend + MySQL com Docker

Este projeto contém uma aplicação backend (Spring Boot) conectada a um banco MySQL, utilizando Docker e Docker Compose.

---

## ✅ Pré-requisitos

- [Docker](https://www.docker.com/)
- [Java 17](https://www.java.com/pt-BR/)

---

## ▶️ Rodando o projeto com Docker

Para rodar a aplicação com docker é necessário primeiramente ter o docker instalado. Após instalação e configuração basta rodar o seguinte comando:
````Bash
docker compose up -build
````
A aplicação está configurada para ouvir o banco Mysql pela porta 3307, logo é necesário confirmar que nada está rodando na porta referida.

Para rodar a aplicação sem docker basta usar alguma IDE de preferencia que suporte rodar aplicações SpringBoot, sendo possível também a opção por linha de comando. O único diferencial para a versão do docker é que o banco foi configurado na porta 3306, padrão do mysql.

Para rodar sem docker, é necessário a criação do banco de dados e suas respectivas tabelas e dados.

Os seguintes comandos são necessários para que a aplicação se comporte da forma esperada:

````Bash
CREATE DATABASE sistema_despesas;

CREATE TABLE IF NOT EXISTS tipos_pagamentos (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS endereco (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    uf VARCHAR(255),
    cep VARCHAR(255),
    municipio VARCHAR(255),
    bairro VARCHAR(255),
    logradouro VARCHAR(255),
    numero VARCHAR(50),
    complemento VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS categorias (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    descricao VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS despesas (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    valor NUMERIC(10,2),
    data_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descricao VARCHAR(255),
    id_tipo_pagamento INT NOT NULL,
    id_categoria INT NOT NULL,
    id_local INT NOT NULL,

    FOREIGN KEY (id_tipo_pagamento) REFERENCES tipos_pagamentos(id),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id),
    FOREIGN KEY (id_local) REFERENCES endereco(id)
);

INSERT INTO tipos_pagamentos (tipo) VALUES
('Cartao de Credito'),
('Credito'),
('Debito'),
('Dinheiro'),
('Pix');

INSERT INTO categorias (nome, descricao) VALUES
('Roupa', 'Bermudas, calças, etc'),
('Tecnologia', 'Notebook, celular, tablet'),
('Comida', 'Alimentos perecíveis'),
('Outros', 'Outros');
````

Em ambas as versões a aplicação está rodando na porta 8080.

## ▶️ Documentação

A documentação dos endpoints foi feita através do swagger. A mesma pode ser acessada após rodar a aplicação através do seguinte link:
 - [swagger](http://localhost:8080/swagger-ui/index.html?url=v3/api-docs)
