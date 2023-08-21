INSERT INTO assistant (assistant_name) 
VALUES 
	('Alfred Pennyworth'),
	('Mary Jane Watson'),
	('Lois Lane');

UPDATE assistant SET supe_id = 1 WHERE assistant_name = 'Alfred Pennyworth';
UPDATE assistant SET supe_id = 2 WHERE assistant_name = 'Mary Jane Watson';
UPDATE assistant SET supe_id = 3 WHERE assistant_name = 'Lois Lane';