drop database defaultdb;
create database defaultdb;
use defaultdb;
create table role(
    id INT primary key auto_increment,
    nome VARCHAR(200),
    authorities VARCHAR(300)
);
create table oficina(
    id INT primary key auto_increment,
    nome_oficina VARCHAR(200),
    voluntario_id INT references voluntario(id),
    horarios VARCHAR(200)
);
create table usuario(
    id INT primary key auto_increment,
    email VARCHAR(200),
    senha VARCHAR(200),
    role_id INT references role(id)
);
create table aluno(
    id INT primary key auto_increment,
    nome_completo VARCHAR(200),
    numero_celular VARCHAR(200),
    email VARCHAR(200),
    senha VARCHAR(200),
    role_id INT references role(id),
    idade INT,
    conhece_programacao BOOL,
    oficina_id INT references oficina(id)
);
create table voluntario(
    id INT primary key auto_increment,
    nome_completo VARCHAR(200),
    numero_celular VARCHAR(200),
    email VARCHAR(200),
    senha VARCHAR(200),
    role_id INT references role(id),
    motivacao VARCHAR(500),
    oficina_id INT references oficina(id)
);
create table contato(
    id INT primary key auto_increment,
    nome_completo VARCHAR(200),
    numero_celular VARCHAR(200),
    email VARCHAR(200),
    senha VARCHAR(200),
    role_id INT references role(id),
    critica_sugestao VARCHAR(500)
);

insert into role (id, nome, authorities) values (
    1, 'ROLE_ALUNO', 'READ'
);

insert into role (id, nome, authorities) values (
    2, 'ROLE_VOLUNTARIO', 'READ'
);

insert into role (id, nome, authorities) values (
    3, 'ROLE_CONTATO', 'READ'
);

insert into oficina (id, nome_oficina, horarios) values (
    1, 'Introdução à lógica de programação', 'SEG/QUA,19:30-21:30'
);