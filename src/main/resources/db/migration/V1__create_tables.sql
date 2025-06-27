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
