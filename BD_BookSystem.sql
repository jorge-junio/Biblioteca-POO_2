CREATE DATABASE IF NOT EXISTS booksystem;
USE booksystem;

CREATE TABLE tb_usuario(
	id_usuario INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT 'CÓDIGO DO USUÁRIO',
	login   VARCHAR(30) NOT NULL COMMENT 'LOGIN DO USUÁRIO PARA ACESSO AO SISTEMA',
	senha   VARCHAR(30) NOT NULL COMMENT 'SENHA DO USUÁRIO PARA ACESSO AO SISTEMA'   	
);

CREATE TABLE livro(
    id            INT AUTO_INCREMENT NOT NULL,
	ano           BIGINT  NOT NULL,
	genero        VARCHAR(70)  NOT NULL,
	prateleira    VARCHAR(70)  NOT NULL,
	autor         VARCHAR(70)  NOT NULL,
	emprestado    VARCHAR(70)  NOT NULL,
	isbn          VARCHAR(70)  NOT NULL,
	editora       VARCHAR(70)  NOT NULL,
	edicao        BIGINT  NOT NULL,
	titulo        VARCHAR(70)  NOT NULL,
    dt_cadastro         DATETIME     NOT NULL COMMENT 'DATA DE CADASTRO DO REGISTRO',
    id_usuario_cadastro	INT	     NOT NULL COMMENT  'USUÁRIO LOGADO QUE CADASTROU O LIVRO',
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario_cadastro) REFERENCES usuario(id_usuario) 
);

CREATE TABLE cliente(
	cpf           VARCHAR(70)  NOT NULL,
	nome          VARCHAR(70)  NOT NULL,
	nascimento    DATETIME     NOT NULL,
	situacao        CHAR(1)	   NOT NULL,
	telefone      VARCHAR(70)  NOT NULL,
	PRIMARY KEY (cpf)
);

CREATE TABLE emprestimo(
	id            INT AUTO_INCREMENT NOT NULL,
	data_saida    DATETIME     NOT NULL,
	data_entrega  DATETIME     NOT NULL,
	cpf           VARCHAR(70)  NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (cpf) REFERENCES cliente(cpf) 
);

INSERT INTO tb_usuario(login, senha) VALUES('admin', 'admin');