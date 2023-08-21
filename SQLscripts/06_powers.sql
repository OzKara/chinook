INSERT INTO power (power_name, power_desc) 
VALUES 
	('Flight', 'Abiltiy to fly'),
	('Rich af', 'Loads of money'),
	('Spiderweb', 'Ability to shoot spiderwebs'),
	('Laservision', 'Abiltiy to fire laser from eyes');

INSERT INTO superheropower (supe_id, power_id)
VALUES
	((SELECT supe_id FROM superhero WHERE supe_name = 'Clark Kent'), (SELECT power_id FROM power WHERE power_name = 'Flight')),
	((SELECT supe_id FROM superhero WHERE supe_name = 'Bruce Wayne'), (SELECT power_id FROM power WHERE power_name = 'Rich af')),
	((SELECT supe_id FROM superhero WHERE supe_name = 'Peter Parker'), (SELECT power_id FROM power WHERE power_name = 'Spiderweb')),
	((SELECT supe_id FROM superhero WHERE supe_name = 'Clark Kent'), (SELECT power_id FROM power WHERE power_name = 'Laservision'));