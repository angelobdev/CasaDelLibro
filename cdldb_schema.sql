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

-- Per il corretto funzionamento del codice il ruolo utente deve avere ID = 1;
INSERT INTO ruoli (id, nome, grado)
VALUES ('ROLE_UTENTE', 1);

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
    descrizione   VARCHAR(255)     NOT NULL,
    autore        VARCHAR(64)      NOT NULL,
    numero_pagine INTEGER          NOT NULL,
    eta_minima    INTEGER          NOT NULL,
    quantita      INTEGER          NOT NULL,
    copertina     VARCHAR(255)     NOT NULL DEFAULT 'https://blog.springshare.com/wp-content/uploads/2010/02/nc-md.gif',
    prezzo        DOUBLE PRECISION NOT NULL DEFAULT 0.0,

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
--
-- Vincoli:
-- 1)   Al momento della creazione un ordine deve essere associato
--      ad una spedizione o un ritiro, ma non ad entrambi
--
-- 2)   Al momento della creazione di un ordine il suo carrello deve
--      aggiornare il flag 'acquistato' (a true)
--
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

-- Preferenze utente, Relazione con libri (Molti a Molti)
CREATE TABLE IF NOT EXISTS preferenze_utenti
(
    id        SERIAL  NOT NULL,
    utente_id INTEGER NOT NULL,
    libro_id  INTEGER NOT NULL,

    FOREIGN KEY (utente_id) REFERENCES utenti (id),
    FOREIGN KEY (libro_id) REFERENCES libri (id),
    PRIMARY KEY (id)
);

-- JOIN TABLE (carrelli - libri), Relazione Molti a Molti
CREATE TABLE IF NOT EXISTS carrelli_libri
(
    id          SERIAL  NOT NULL,
    carrello_id INTEGER NOT NULL,
    libro_id    INTEGER NOT NULL,

    FOREIGN KEY (carrello_id) REFERENCES carrelli (id),
    FOREIGN KEY (libro_id) REFERENCES libri (id),
    PRIMARY KEY (id)
);

--- *** VINCOLI *** ---

-- 1)   Al momento della creazione un ordine deve essere associato
--      ad una spedizione o un ritiro, ma non ad entrambi
--
CREATE OR REPLACE FUNCTION check_ritiro_spedizione_ordine()
    RETURNS TRIGGER AS
$$
BEGIN
    -- Controllo che non esistano entrambi
    IF NEW.spedizione_id IS NOT NULL AND NEW.ritiro_id IS NOT NULL THEN
        RAISE EXCEPTION 'Un ordine non può essere associato sia a una spedizione che a un ritiro';
    END IF;

    -- Controllo che esista almeno uno dei due
    IF NEW.spedizione_id IS NULL AND NEW.ritiro_id IS NULL THEN
        RAISE EXCEPTION 'Un ordine deve essere associato almeno ad un ritiro o ad una spedizione';
    end if;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER ritiro_spedizione_ordine
    BEFORE INSERT
    ON ordini
    FOR EACH ROW
EXECUTE FUNCTION check_ritiro_spedizione_ordine();

-- 2)   Al momento della creazione di un ordine il suo carrello deve
--      aggiornare il flag 'acquistato' (a true)
--

CREATE OR REPLACE FUNCTION set_acquistato_flag_true()
    RETURNS TRIGGER AS
$$
BEGIN
    -- Aggiorno il carrello corrispondente
    UPDATE carrelli SET acquistato= true WHERE id = NEW.carrello_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER acquistato_flag_true_ordine
    AFTER INSERT
    ON ordini
    FOR EACH ROW
EXECUTE FUNCTION set_acquistato_flag_true();