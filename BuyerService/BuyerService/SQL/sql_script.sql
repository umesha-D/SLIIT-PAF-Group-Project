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

CREATE TABLE favourites(
	userId INT NOT NULL AUTO_INCREMENT,
	productId INT NOT NULL,
	addedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	
	CONSTRAINT pk_favourites PRIMARY KEY(userId, productId),
	CONSTRAINT fk_buyer FOREIGN KEY(userId) REFERENCES buyer(id)
);

-- dummy data
INSERT INTO buyer(id, userName, password, email)
VALUES
(1,"Javid Jones", "sljdkhsisdsd98^&%tg", "javid@gmail.com" ),
(2,"Miller Andrew", "skjd7dsd4s6fds^7j", "miller@gmail.com" ),
(3,"Jon doe", "ssdsdsdjskdsdsd", "jon@gmail.com" ),
(4,"James Cameron", "sksdsdopio03s^]e7j", "james@gmail.com" ),
(5,"Mohomad Ali", "ds;dlsodjsdlsd^soidhsod", "ali@gmail.com" );


INSERT INTO favourites(userId, productId)
VALUES
(1,1),
(1,2),
(2,1),
(2,4);

