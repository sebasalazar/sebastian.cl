BEGIN TRANSACTION;

DROP TABLE IF EXISTS usuarios CASCADE;
CREATE TABLE usuarios (
    pk bigserial NOT NULL,
    usuario varchar(255) NOT NULL,
    contrasena varchar(255) NOT NULL,
    nombres varchar(255) NOT NULL,
    apellidos varchar(255) NOT NULL,
    UNIQUE (usuario),
    PRIMARY KEY(pk)
);

DROP TABLE IF EXISTS tips CASCADE;
CREATE TABLE tips (
    pk bigserial NOT NULL,
    fecha timestamp NOT NULL DEFAULT NOW(),
    titulo varchar(255) NOT NULL,
    tip text,
    PRIMARY KEY (pk)
);

DROP TABLE IF EXISTS posts CASCADE;
CREATE TABLE posts (
    pk bigserial NOT NULL,
    fecha timestamp NOT NULL DEFAULT NOW(),
    autor varchar(255) NOT NULL,
    titulo varchar(255) NOT NULL,
    texto text,
    PRIMARY KEY (pk)
);

DROP TABLE IF EXISTS comentarios CASCADE;
CREATE TABLE comentarios (
    pk bigserial NOT NULL,
    post_fk int NOT NULL REFERENCES posts(pk) ON UPDATE CASCADE ON DELETE CASCADE,
    fecha timestamp NOT NULL DEFAULT NOW(),
    autor varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    web varchar(255) NOT NULL,
    comentario text,
    PRIMARY KEY (pk)
);

COMMIT;
