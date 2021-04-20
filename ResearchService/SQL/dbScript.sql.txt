DROP DATABASE IF EXISTS buyerDB;

CREATE DATABASE buyerDB;
USE buyerDB;

SET @@SESSION.auto_increment_increment=1;

CREATE TABLE buyer(
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    userName VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	regiteredAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updatedAt TIMESTAMP DEFAULT NULL,
	
	CONSTRAINT pk_buyer PRIMARY KEY(id)
);


-- dummy data
INSERT INTO buyer(userName, password, email)
VALUES
("Javid Jones", "sljdkhsisdsd98^&%tg", "javid@gmail.com" ),
("Miller Andrew", "skjd7dsd4s6fds^7j", "miller@gmail.com" ),
("Jon doe", "ssdsdsdjskdsdsd", "jon@gmail.com" ),
("James Cameron", "sksdsdopio03s^]e7j", "james@gmail.com" ),
("Mohomad Ali", "ds;dlsodjsdlsd^soidhsod", "ali@gmail.com" );



