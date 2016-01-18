CREATE TABLE users (

	id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(32) NOT NULL,
	last_name VARCHAR(32) NOT NULL,
	last_name VARCHAR(64) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(32) NOT NULL,
	PRIMARY KEY ( id )

);
INSERT INTO users (first_name,last_name,email,password) VALUES("John","Smith","jsmith@testing123.com", "testing123");
INSERT INTO users (first_name,last_name,email,password) VALUES("Michael","Jordan","michael11@testing123.com", "testing123");
INSERT INTO users (first_name,last_name,email,password) VALUES("Poopy","Man","poop@man.testing123.com", "testing123");
INSERT INTO users (first_name,last_name,email,password) VALUES("Test","Test","supertest@testing123.com", "testing123");
