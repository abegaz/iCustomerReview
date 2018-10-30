<<<<<<< HEAD
=======
<<<<<<< HEAD
CREATE SCHEMA REVIEW_PROJECT_DB;

CREATE TABLE REG_USER (
	account_ID CHAR(9) PRIMARY KEY NOT NULL,
	username VARCHAR(32) UNIQUE NOT NULL,
	display_name VARCHAR(64),
	password_hash CHAR(128) NOT NULL,
	password_salt CHAR(32) NOT NULL,
	email VARCHAR(64) UNIQUE NOT NULL,
	is_banned TINYINT(1) DEFAULT 0);

CREATE TABLE REVIEW (
	review_ID CHAR(9) PRIMARY KEY NOT NULL,
	posting_account_ID CHAR(9),
	display_email CHAR(64),
	disp_name CHAR(32),
	post_date_time TIMESTAMP(),
	posting_IP VARCHAR(26) DEFAULT "0.0.0.0",
	review_text TEXT(2000) NOT NULL,
	is_valid TINYINT(1) DEFAULT 1 NOT NULL,
=======
>>>>>>> working
CREATE SCHEMA REVIEW_PROJECT_DB;

CREATE TABLE REG_USER (
	account_ID CHAR(9) PRIMARY KEY NOT NULL,
	username VARCHAR(32) UNIQUE NOT NULL,
	display_name VARCHAR(64),
	password_hash CHAR(128) NOT NULL,
	password_salt CHAR(32) NOT NULL,
	email VARCHAR(64) UNIQUE NOT NULL,
	is_banned TINYINT(1) DEFAULT 0);

CREATE TABLE REVIEW (
	review_ID CHAR(9) PRIMARY KEY NOT NULL,
	posting_account_ID CHAR(9),
	display_email CHAR(64),
	disp_name CHAR(32),
	post_date_time TIMESTAMP(),
	posting_IP VARCHAR(26) DEFAULT "0.0.0.0",
	review_text TEXT(2000) NOT NULL,
	is_valid TINYINT(1) DEFAULT 1 NOT NULL,
<<<<<<< HEAD
=======
>>>>>>> re-commit to local
>>>>>>> working
	FOREIGN KEY(posting_account_ID) REFERENCES REG_USER(account_ID) ON DELETE CASCADE ON UPDATE CASCADE);