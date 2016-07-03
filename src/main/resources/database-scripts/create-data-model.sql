CREATE DATABASE bankPostgres;

DROP TABLE IF EXISTS users;
  CREATE TABLE users
  (
    users_id uuid NOT NULL,
    name character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    CONSTRAINT users_id_pk PRIMARY KEY (users_id)
  )
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;


