CREATE TABLE Kino
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    film VARCHAR(255) NOT NULL,
    antall INTEGER NOT NULL,
    fornavn VARCHAR(255) NOT NULL,
    etternavn VARCHAR(255) NOT NULL,
    tlfNmr INTEGER NOT NULL,
    epost VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Bruker
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    brukernavn VARCHAR(255) NOT NULL,
    passord VARCHAR(255)NOT NULL,
    PRIMARY KEY(id)
);