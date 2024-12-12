CREATE DATABASE IF NOT EXISTS nave_espacial_titanic
	DEFAULT CHARACTER SET utf8mb3
    COLLATE utf8mb3_spanish2_ci;
USE nave_espacial_titanic;
CREATE TABLE pasajero (
	id			INTEGER     AUTO_INCREMENT,
    nombre		VARCHAR(90) NOT NULL, -- Incluye nombres y apellidos
    edad		INTEGER     NOT NULL,
    estatus		BOOLEAN     DEFAULT false, -- True si es VIP, false si no
    n_planeta	VARCHAR(20) NOT NULL,
    n_p_origen	VARCHAR(20) NOT NULL,
    num_cabina	INTEGER     NOT NULL,
    PRIMARY KEY (id),
    KEY (n_planeta),
    KEY (n_p_origen),
    CONSTRAINT planeta_origen
        FOREIGN KEY(n_planeta)
        REFERENCES planeta(nombre)
        ON DELETE NO ACTION
        ON UPDATE CASCADE
    CONSTRAINT planeta_destino
        FOREIGN KEY(n_p_origen)
        REFERENCES planeta(nombre)
        ON DELETE NO ACTION 
        ON UPDATE CASCADE 
);
CREATE TABLE planeta (
    nombre          VARCHAR(20) NOT NULL, 
    masa            INTEGER     NOT NULL,
    radio           INTEGER     NOT NULL,
    nombre_sistema  VARCHAR(20) NOT NULL,
    PRIMARY KEY (nombre)
);
CREATE TABLE satelite (
    -- nombre          VARCHAR(20) NOT NULL, 
    -- masa            INTEGER     NOT NULL,
    -- radio           INTEGER     NOT NULL,
    -- nombre_sistema  VARCHAR(20) NOT NULL,
    -- PRIMARY KEY (nombre)
);
CREATE TABLE sistema (
    -- nombre          VARCHAR(20) NOT NULL, 
    -- masa            INTEGER     NOT NULL,
    -- radio           INTEGER     NOT NULL,
    -- nombre_sistema  VARCHAR(20) NOT NULL,
-- PRIMARY KEY (nombre))
);

CREATE TABLE compuesto ();
CREATE TABLE elemento_quimico ();
CREATE TABLE cabina ();
CREATE TABLE cubierta();

