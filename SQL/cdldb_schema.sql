-- ***      Codice SQL - Progetto "La Casa del Libro"       *** --
-- ***          Angelo Belcastro - Mat.209893               *** --
-- ***    Esame: Piattaforme e Sistemi Web, A.A.2022/23     *** --


-- Ruoli
CREATE TABLE IF NOT EXISTS ruoli
(
    id    SERIAL      NOT NULL,
    nome  VARCHAR(32) NOT NULL,
    grado INTEGER     NOT NULL,

    PRIMARY KEY (id)
);

INSERT INTO ruoli (nome, grado)
VALUES ('ROLE_USER', 1);

INSERT INTO ruoli (nome, grado)
VALUES ('ROLE_ADMIN', 999);

-- Utenti
CREATE TABLE IF NOT EXISTS utenti
(
    id           SERIAL       NOT NULL,

    -- Informazioni personali
    nome         VARCHAR(32)  NOT NULL,
    cognome      VARCHAR(32)  NOT NULL,
    data_nascita TIMESTAMP    NOT NULL,

    -- Dati account
    username     VARCHAR(32)  NOT NULL,
    email        VARCHAR(64)  NOT NULL,
    password     VARCHAR(255) NOT NULL,
    avatar       VARCHAR(255) NOT NULL,
    ruolo_id     INTEGER      NOT NULL,

    FOREIGN KEY (ruolo_id) REFERENCES ruoli (id),
    PRIMARY KEY (id)
);

-- Libri
CREATE TABLE IF NOT EXISTS libri
(
    id            SERIAL           NOT NULL,

    -- Informazioni libro
    titolo        VARCHAR(64)      NOT NULL,
    descrizione   VARCHAR(2048)    NOT NULL,
    autore        VARCHAR(64)      NOT NULL,
    numero_pagine INTEGER          NOT NULL,
    eta_minima    INTEGER          NOT NULL,
    quantita      INTEGER          NOT NULL,
    copertina     VARCHAR(255)     NOT NULL DEFAULT 'https://blog.springshare.com/wp-content/uploads/2010/02/nc-md.gif',
    prezzo        DOUBLE PRECISION NOT NULL DEFAULT 0.0,
    editore       VARCHAR(64)      NOT NULL DEFAULT 'Anonimo',

    PRIMARY KEY (id)
);

-- Carrelli
CREATE TABLE IF NOT EXISTS carrelli
(
    id             SERIAL           NOT NULL,

    -- Informazioni carrello
    data_creazione TIMESTAMP        NOT NULL,
    importo        DOUBLE PRECISION NOT NULL,
    acquistato     BOOLEAN          NOT NULL,
    utente_id      INTEGER          NOT NULL,

    FOREIGN KEY (utente_id) REFERENCES utenti (id),
    PRIMARY KEY (id)
);

-- Ritiri
CREATE TABLE IF NOT EXISTS ritiri
(
    id               SERIAL      NOT NULL,

    -- Informazioni ritiro
    data_disponibile TIMESTAMP   NOT NULL,
    data_ritiro      TIMESTAMP,
    stato            VARCHAR(32) NOT NULL,

    PRIMARY KEY (id)
);

-- Spedizioni
CREATE TABLE IF NOT EXISTS spedizioni
(
    id              SERIAL       NOT NULL,

    -- Informazioni spedizione
    destinazione    VARCHAR(255) NOT NULL,
    data_spedizione TIMESTAMP    NOT NULL,
    data_consegna   TIMESTAMP,
    stato           VARCHAR(32)  NOT NULL,

    PRIMARY KEY (id)
);

-- Ordini
CREATE TABLE IF NOT EXISTS ordini
(
    id            SERIAL           NOT NULL,

    -- Informazioni ordine
    data          TIMESTAMP        NOT NULL,
    importo       DOUBLE PRECISION NOT NULL,
    carrello_id   INTEGER          NOT NULL,
    spedizione_id INTEGER,
    ritiro_id     INTEGER,

    FOREIGN KEY (carrello_id) REFERENCES carrelli (id),
    FOREIGN KEY (spedizione_id) REFERENCES spedizioni (id),
    FOREIGN KEY (ritiro_id) REFERENCES ritiri (id),
    PRIMARY KEY (id)
);

-- Relazione UTENTE - LIBRO (Molti a Molti) [Indica i libri preferiti dell'utente]
CREATE TABLE IF NOT EXISTS preferenze_utenti
(
    id        SERIAL  NOT NULL,
    utente_id INTEGER NOT NULL,
    libro_id  INTEGER NOT NULL,

    FOREIGN KEY (utente_id) REFERENCES utenti (id),
    FOREIGN KEY (libro_id) REFERENCES libri (id),
    PRIMARY KEY (id)
);

-- Relazione CARRELLO - LIBRO (Molti a Molti) [Indica i libri presenti in un determinato carrello]
CREATE TABLE IF NOT EXISTS carrelli_libri
(
    id          SERIAL  NOT NULL,
    carrello_id INTEGER NOT NULL,
    libro_id    INTEGER NOT NULL,
    quantita    INTEGER NOT NULL DEFAULT 1,

    FOREIGN KEY (carrello_id) REFERENCES carrelli (id),
    FOREIGN KEY (libro_id) REFERENCES libri (id),
    PRIMARY KEY (id)
);