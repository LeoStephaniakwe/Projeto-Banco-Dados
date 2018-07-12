DROP DATABASE IF  EXISTS exemplo_bancos_dados_01;
CREATE DATABASE IF NOT EXISTS exemplo_bancos_dados_01;
USE exemplo_bancos_dados_01;

CREATE TABLE clientes(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(11),
    data_nascimento DATE,
    cpf VARCHAR(11),
    ativo BOOLEAN DEFAULT true);



