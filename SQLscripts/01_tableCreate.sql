DROP TABLE IF EXISTS superhero, power, assistant;

CREATE TABLE superhero (
	supe_id SERIAL PRIMARY KEY,
	supe_name varchar(50) NOT NULL,
	supe_alias varchar(50) NOT NULL,
	supe_origin varchar(50) NOT NULL
);

CREATE TABLE assistant (
	assistant_id SERIAL PRIMARY KEY,
	assistant_name varchar(50) NOT NULL
);

CREATE TABLE power (
	power_id SERIAL PRIMARY KEY,
	power_name varchar(50) NOT NULL,
	power_desc varchar(150) NOT NULL
);



