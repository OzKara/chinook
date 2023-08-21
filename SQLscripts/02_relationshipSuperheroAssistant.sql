ALTER TABLE assistant
ADD COLUMN supe_id INT REFERENCES superhero(supe_id);

ALTER TABLE assistant
ADD CONSTRAINT fk_assistant_superhero
FOREIGN KEY (supe_id)
REFERENCES superhero(supe_id)
ON DELETE CASCADE;
