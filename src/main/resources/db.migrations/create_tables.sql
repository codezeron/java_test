-- Criando a tabela CLIENT
CREATE TABLE IF NOT EXISTS CLIENT (
  id INTEGER PRIMARY KEY,
  NAME VARCHAR(250) NOT NULL
);

-- Criando a tabela MODULE
CREATE TABLE IF NOT EXISTS MODULE (
  id INTEGER PRIMARY KEY,
  NAME VARCHAR(250) NOT NULL
);

-- Criando a tabela TICKET
CREATE TABLE IF NOT EXISTS TICKET (
    id BIGSERIAL PRIMARY KEY,
    TITLE VARCHAR(250) NOT NULL,
    FK_ID_CLIENT INTEGER NOT NULL,
    OPENING_DATE TIMESTAMP NOT NULL,
    CLOSING_DATE TIMESTAMP NOT NULL,
    FK_ID_MODULE INTEGER NOT NULL,
    FOREIGN KEY (FK_ID_CLIENT) REFERENCES CLIENT(id),
    FOREIGN KEY (FK_ID_MODULE) REFERENCES MODULE(id)
);
