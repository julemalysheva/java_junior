CREATE SCHEMA IF NOT EXISTS SchoolDB;
USE SchoolDB;

CREATE TABLE IF NOT EXISTS Courses
(
    id       INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(255),
    duration INT
);
