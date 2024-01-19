CREATE TABLE USERS ( userid int NOT NULL AUTO_INCREMENT, username varchar(255), email varchar(255), password varchar(255), role varchar(255), primary key (userid));
CREATE TABLE TOKENS ( tokenid int NOT NULL AUTO_INCREMENT, token varchar(255), admin BOOLEAN, expiry DATETIME, primary key (tokenid));
CREATE TABLE PROJECTS ( projectid int NOT NULL AUTO_INCREMENT, name varchar(255), description varchar(255), primary key (projectid));
CREATE TABLE ENTRIES ( entryid int NOT NULL AUTO_INCREMENT, projectid int, date DATETIME, hours int, description varchar(255), primary key (entryid));