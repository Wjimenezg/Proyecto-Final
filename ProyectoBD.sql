-- Creo la base de datos a usar
CREATE DATABASE IF NOT EXISTS jugueteriaDB;

-- usar la base de datos
USE jugueteriaDB;

CREATE TABLE Juguete (
    idJuguete INT AUTO_INCREMENT PRIMARY KEY,
    tipoJuguete VARCHAR(255) NOT NULL,
    tamanio VARCHAR(50) NOT NULL
);

CREATE TABLE Inventario (
    idInventario INT AUTO_INCREMENT PRIMARY KEY,
    idJuguete INT NOT NULL,
    Cantidad INT NOT NULL,
    clasiJuguete VARCHAR(255) NOT NULL,
    FOREIGN KEY (idJuguete) REFERENCES Juguete(idJuguete)
);

CREATE TABLE Facturacion (
    idFactura INT AUTO_INCREMENT PRIMARY KEY,
    idJuguete INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    Cantidad INT NOT NULL,
    precio DECIMAL (10, 2) NOT NULL,
    tipoJuguete VARCHAR(255) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idJuguete) REFERENCES Juguete(idJuguete)
);


CREATE TABLE Pedido (
    idPedido INT AUTO_INCREMENT PRIMARY KEY,
    idInventario INT NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idInventario) REFERENCES Inventario(idInventario)
);

ALTER TABLE facturacion DROP FOREIGN KEY IF EXISTS fk_idPedido;
DROP TABLE IF EXISTS Facturacion;

CREATE TABLE Facturacion (
    idFactura INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR (100) NOT NULL,
    tipoJuguete VARCHAR (255) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idjuguete) REFERENCES Juguete(idJuguete)
);

CREATE TABLE Facturacion (
    idFactura INT AUTO_INCREMENT PRIMARY KEY,
    idPedido INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idPedido) REFERENCES Pedido(idPedido)
);

CREATE TABLE Facturacion (
    idFactura INT AUTO_INCREMENT PRIMARY KEY,
    idJuguete INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    Cantidad INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idJuguete) REFERENCES Juguete(idJuguete)
);


INSERT INTO Juguete (tipoJuguete, tamanio) VALUES ('Pelota', 'Pequeña');
INSERT INTO Juguete (tipoJuguete, tamanio) VALUES ('Muñeca', 'Mediana');
INSERT INTO Juguete (tipoJuguete, tamanio) VALUES ('Rompecabezas', 'Grande');
INSERT INTO Juguete (tipoJuguete, tamanio) VALUES ('Lego', 'Pequeño');
INSERT INTO Juguete (tipoJuguete, tamanio) VALUES ('Carro de juguete', 'Mediano');

INSERT INTO Inventario (idJuguete, Cantidad, clasiJuguete) VALUES (1, 50, 'Deportes');
INSERT INTO Inventario (idJuguete, Cantidad, clasiJuguete) VALUES (2, 30, 'Muñecas');
INSERT INTO Inventario (idJuguete, Cantidad, clasiJuguete) VALUES (3, 20, 'Rompecabezas');
INSERT INTO Inventario (idJuguete, Cantidad, clasiJuguete) VALUES (4, 100, 'Construcción');
INSERT INTO Inventario (idJuguete, Cantidad, clasiJuguete) VALUES (5, 45, 'Vehículos');

INSERT INTO Pedido (idInventario, cantidad, precio) VALUES (1, 5, 30.00);
INSERT INTO Pedido (idInventario, cantidad, precio) VALUES (2, 3, 25.50);
INSERT INTO Pedido (idInventario, cantidad, precio) VALUES (3, 2, 35.00);
INSERT INTO Pedido (idInventario, cantidad, precio) VALUES (4, 10, 22.75);
INSERT INTO Pedido (idInventario, cantidad, precio) VALUES (5, 4, 28.25);

INSERT INTO Facturacion (idPedido, total) VALUES (1, 150.00);
INSERT INTO Facturacion (idPedido, total) VALUES (2, 90.50);
INSERT INTO Facturacion (idPedido, total) VALUES (3, 75.00);
INSERT INTO Facturacion (idPedido, total) VALUES (4, 250.75);
INSERT INTO Facturacion (idPedido, total) VALUES (5, 120.25);

INSERT INTO Facturacion (nombre, tipoJuguete, total) VALUES ('Nombre1', 'Tipo1', 150.00);
INSERT INTO Facturacion (nombre, tipoJuguete, total) VALUES ('Nombre2', 'Tipo2', 90.50);
INSERT INTO Facturacion (nombre, tipoJuguete, total) VALUES ('Nombre3', 'Tipo3', 75.00);
INSERT INTO Facturacion (nombre, tipoJuguete, total) VALUES ('Nombre4', 'Tipo4', 250.75);
INSERT INTO Facturacion (nombre, tipoJuguete, total) VALUES ('Nombre5', 'Tipo5', 120.25);

-- Suponiendo que el idJuguete en Facturacion corresponde a un juguete existente en la tabla Juguete
INSERT INTO Facturacion (idJuguete, nombre, tipoJuguete, total) VALUES (1, 'Nombre1', 'Tipo1', 150.00);
INSERT INTO Facturacion (idJuguete, nombre, tipoJuguete, total) VALUES (2, 'Nombre2', 'Tipo2', 90.50);
INSERT INTO Facturacion (idJuguete, nombre, tipoJuguete, total) VALUES (3, 'Nombre3', 'Tipo3', 75.00);
INSERT INTO Facturacion (idJuguete, nombre, tipoJuguete, total) VALUES (4, 'Nombre4', 'Tipo4', 250.75);
INSERT INTO Facturacion (idJuguete, nombre, tipoJuguete, total) VALUES (5, 'Nombre5', 'Tipo5', 120.25);

INSERT INTO Facturacion (idJuguete, nombre, Cantidad, total) VALUES (1, 'Cliente 1', 3, (SELECT precio FROM Juguete WHERE idJuguete = 1) * 3);
INSERT INTO Facturacion (idJuguete, nombre, Cantidad, total) VALUES (2, 'Cliente 2', 2, (SELECT precio FROM Juguete WHERE idJuguete = 2) * 2);
INSERT INTO Facturacion (idJuguete, nombre, Cantidad, total) VALUES (3, 'Cliente 3', 5, (SELECT precio FROM Juguete WHERE idJuguete = 3) * 5);
INSERT INTO Facturacion (idJuguete, nombre, Cantidad, total) VALUES (4, 'Cliente 4', 4, (SELECT precio FROM Juguete WHERE idJuguete = 4) * 4);
INSERT INTO Facturacion (idJuguete, nombre, Cantidad, total) VALUES (5, 'Cliente 5', 4, (SELECT precio FROM Juguete WHERE idJuguete = 5) * 4);

ALTER TABLE Juguete
ADD COLUMN precio DECIMAL (10,2);  -- Añadir una columna a una tabla

ALTER TABLE FACTURACION
ADD COLUMN nombre VARCHAR (50);

DELETE FROM Juguete WHERE  idJuguete=10;  -- Eliminar dato de un columna en especifico

SELECT * FROM juguete;
SELECT * FROM facturacion;
SELECT * FROM inventario;

UPDATE juguete SET PRECIO= 95.50 WHERE idJuguete=5; -- añadir valores a una celda en especifico
UPDATE inventario SET Cantidad= 95 WHERE idJuguete=1;
DROP TABLE facturacion;

DELETE FROM facturacion WHERE  idFactura=6;
INSERT INTO facturacion (idfactura, idJuguete, nombre, cantidad, total) VALUES (6,1,'nombre',cantidadPedido,total)"
SELECT CANTIDAD FROM INVENTARIO WHERE IDJUGUETE =1
SELECT CANTIDAD FROM INVENTARIO WHERE IDJUGUETE =1