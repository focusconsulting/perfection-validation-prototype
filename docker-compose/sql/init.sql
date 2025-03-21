CREATE ROLE "irspv" WITH
    LOGIN
    SUPERUSER
    INHERIT
    CREATEDB
    CREATEROLE
    NOREPLICATION
    PASSWORD 'irspv-pass';

CREATE DATABASE irspv
    WITH
    OWNER = "irspv"
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;