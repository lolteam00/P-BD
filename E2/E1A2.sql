CREATE DATABASE IF NOT EXISTS nave_espacial_titanic
	DEFAULT CHARACTER SET utf8mb3
    COLLATE utf8mb3_spanish2_ci;
USE nave_espacial_titanic;
CREATE TABLE nave_espacial_titanic.pasajero (
	id			INTEGER AUTO_INCREMENT,
    nombre		VARCHAR(90) NOT NULL, -- Incluye nombres y apellidos
    edad		INTEGER NOT NULL,
    estatus		BOOLEAN DEFAULT false, -- True si es VIP, false si no --
    n_planeta	VARCHAR(20) NOT NULL,
    n_p_origen	VARCHAR(20) NOT NULL,
    num_cabina	INTEGER NOT NULL,
    PRIMARY KEY (id)
)

