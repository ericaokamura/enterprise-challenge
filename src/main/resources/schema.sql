create database games_db;
use games_db;
create table usuario(
    id INT primary key auto_increment,
    nome_usuario VARCHAR(50),
    senha VARCHAR(200)
);
