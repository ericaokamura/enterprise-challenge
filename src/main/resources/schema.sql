use defaultdb;
create table usuario(
    id INT primary key auto_increment,
    nome_usuario VARCHAR(100),
    senha VARCHAR(200)
);
create table aluno(
    id INT primary key auto_increment,
    nome_completo VARCHAR(200),
    numero_celular VARCHAR(200),
    email VARCHAR(200),
    numero_identificacao VARCHAR(200),
    idade INT,
    conhece_programacao BOOL
);
create table voluntario(
    id INT primary key auto_increment,
    nome_completo VARCHAR(200),
    numero_celular VARCHAR(200),
    email VARCHAR(200),
    numero_identificacao VARCHAR(200),
    motivacao VARCHAR(500)
);
create table contato(
    id INT primary key auto_increment,
    nome_completo VARCHAR(200),
    numero_celular VARCHAR(200),
    email VARCHAR(200),
    critica_sugestao VARCHAR(500)
);
