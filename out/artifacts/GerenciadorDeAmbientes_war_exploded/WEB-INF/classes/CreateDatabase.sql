CREATE DATABASE gerenciadordeambientes;

USE gerenciadordeambientes;

CREATE TABLE comodo(
Id INT(10) NOT NULL AUTO_INCREMENT,
PRIMARY KEY (Id));

CREATE TABLE sala(
Id INT(10) NOT NULL,
Description VARCHAR(5000),
FOREIGN KEY (Id) REFERENCES comodo(Id));

CREATE TABLE cozinha(
Id INT(10) NOT NULL,
Description VARCHAR(5000),
FOREIGN KEY (Id) REFERENCES comodo(Id));

CREATE TABLE quarto(
Id INT(10) NOT NULL,
Description VARCHAR(5000),
FOREIGN KEY (Id) REFERENCES comodo(Id));

CREATE TABLE comodocomposto(
Id INT(10) NOT NULL,
Description VARCHAR(5000),
FOREIGN KEY (Id) REFERENCES comodo(Id));

CREATE TABLE mobilia(
Id INT(10) NOT NULL AUTO_INCREMENT,
Description VARCHAR(5000),
DeliveryTime INT,
Cost FLOAT,
PRIMARY KEY (Id));

CREATE TABLE comodocomposto_comodo(
ComodoComposto_Id INT(10) NOT NULL,
Composition_Id INT(10) NOT NULL,
FOREIGN KEY (ComodoComposto_Id) REFERENCES comodocomposto(Id),
FOREIGN KEY (Composition_Id) REFERENCES comodo(Id));

CREATE TABLE comodo_mobilia(
comodoId INT(10) NOT NULL,
mobiliaId INT(10) NOT NULL,
FOREIGN KEY (mobiliaId) REFERENCES mobilia(Id),
FOREIGN KEY (comodoId) REFERENCES comodo(Id));

