use test;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `test`.`users` (
  `id` INT NOT NULL,
  `username` VARCHAR(100) NULL,
  `password` VARCHAR(100) NULL,
  `age` INT NULL,
  PRIMARY KEY (`id`));
  
  INSERT INTO users VALUES (101, 'sally', 'test123', 34);
  INSERT INTO users VALUES (102, 'Andrew', 'test123', 28);
  INSERT INTO users VALUES (103, 'John', 'test123', 39);
  INSERT INTO users VALUES (104, 'Michael', 'test123', 51);
  INSERT INTO users VALUES (105, 'David', 'test123', 45);
  INSERT INTO users VALUES (106, 'Amar', 'test123', 27);
  INSERT INTO users VALUES (107, 'Chris', 'test123', 60);
  INSERT INTO users VALUES (108, 'Damian', 'test123', 55);
