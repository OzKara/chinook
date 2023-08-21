DROP TABLE IF EXISTS superheropower;

CREATE TABLE superheropower (
	supe_id int REFERENCES superhero,
	power_id int REFERENCES power,
	PRIMARY KEY (supe_id, power_id)
);

ALTER TABLE superheropower
ADD CONSTRAINT fk_superheropower_superhero
FOREIGN KEY (supe_id)
REFERENCES superhero(supe_id) ON DELETE CASCADE;

ALTER TABLE superheropower
ADD CONSTRAINT fk_superheropower_power
FOREIGN KEY (power_id)
REFERENCES power(power_id) ON DELETE CASCADE;