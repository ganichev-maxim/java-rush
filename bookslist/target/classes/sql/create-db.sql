USE test;

DROP TABLE IF EXISTS book;

CREATE TABLE book(
	id 			INT(11) 		NOT NULL AUTO_INCREMENT,
    title 		VARCHAR(100) 	NOT NULL,
    description VARCHAR(255),
    author		VARCHAR(100)	NOT NULL,
    isbn		VARCHAR(20)		NOT NULL,
    printYear	INT(4)			NOT NULL,
    readAlready	BOOLEAN			NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

