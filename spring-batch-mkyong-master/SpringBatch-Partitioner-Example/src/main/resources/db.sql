--create database toga;

use toga;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `toga`.`user` (
  `id` INT NOT NULL,
  `username` VARCHAR(100) NULL,
  `password` VARCHAR(100) NULL,
  `age` INT NULL,
  PRIMARY KEY (`id`)
);


/*INSERT INTO USER (id, username, password, age) VALUES ('111','chris','chris@123',45);
INSERT INTO USER (id, username, password, age) VALUES ('112','savani','savani@123',38);
INSERT INTO USER (id, username, password, age) VALUES ('113','neha','neha@123',25);
INSERT INTO USER (id, username, password, age) VALUES ('114','deepak','deepak@123',42);
INSERT INTO USER (id, username, password, age) VALUES ('115','john','john@123',49);
INSERT INTO USER (id, username, password, age) VALUES ('116','nancy','nancy@123',10);
INSERT INTO USER (id, username, password, age) VALUES ('117','amit','amit@123',33);
INSERT INTO USER (id, username, password, age) VALUES ('118','kaveri','kaveri@123',30);
INSERT INTO USER (id, username, password, age) VALUES ('119','meet','meet@123',76);
INSERT INTO USER (id, username, password, age) VALUES ('120','lavina','lavina@',20);
INSERT INTO USER (id, username, password, age) VALUES ('121','tanavi','tanavi@123',38);
INSERT INTO USER (id, username, password, age) VALUES ('122','rashid','rashi@123',45);
INSERT INTO USER (id, username, password, age) VALUES ('123','mangesh','mangesh@123',50);
INSERT INTO USER (id, username, password, age) VALUES ('124','gaurav','gaurav@123',66);
INSERT INTO USER (id, username, password, age) VALUES ('125','lekhani','lekhani@123',44);
INSERT INTO USER (id, username, password, age) VALUES ('126','megha','megha@123',12);
INSERT INTO USER (id, username, password, age) VALUES ('127','virat','virat@123',88);
INSERT INTO USER (id, username, password, age) VALUES ('128','pankaj','pankaj@123',27);
INSERT INTO USER (id, username, password, age) VALUES ('129','prateek','prateek@123',44);
INSERT INTO USER (id, username, password, age) VALUES ('130','parag','parag@123',44);
INSERT INTO USER (id, username, password, age) VALUES ('131','sandeep','sandeep@123',22);
INSERT INTO USER (id, username, password, age) VALUES ('132','vinit','vinit@123',41);
INSERT INTO USER (id, username, password, age) VALUES ('133','ravi','ravi@123',19);
INSERT INTO USER (id, username, password, age) VALUES ('134','gopal','gopal@123',77);
INSERT INTO USER (id, username, password, age) VALUES ('135','harshita','harshita@123',26);
INSERT INTO USER (id, username, password, age) VALUES ('136','sudnya','sudnya@123',30); */