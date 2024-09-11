/* Cria o Banco de Dados */
create database login_gpt;

/* "Usa" o Banco de Dados */
use login_gpt;

/* Tabela Usuário */
create table usuario(
	id_usuario int not null auto_increment,
    email_usuario varchar(254),
    senha_usuario varchar(128),
    primary key(id_usuario)
);