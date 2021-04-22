DROP DATABASE IF EXISTS fbodyDB;

CREATE DATABASE fbodyDB;
USE fbodyDB;

SET @@SESSION.auto_increment_increment=1;

CREATE TABLE fbody(
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    userName VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	regiteredAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updatedAt TIMESTAMP DEFAULT NULL,
	
	CONSTRAINT pk_fbody PRIMARY KEY(id)
);


-- dummy data
INSERT INTO fbody(userName, password, email)
VALUES
("Virat Kolhi", "sljdkhsisdsd98^&%tg", "Virat@gmail.com" ),
("Milan Perera Andrew", "skjd7dsd4s6fds^7j", "Milan@gmail.com" ),
("Silva doe", "ssdsdsdjskdsdsd", "Silva@gmail.com" ),
("James Wilson", "sksdsdopio03s^]e7j", "Wilson@gmail.com" ),
("Mohomad Ali", "ds;dlsodjsdlsd^soidhsod", "ali@gmail.com" );



