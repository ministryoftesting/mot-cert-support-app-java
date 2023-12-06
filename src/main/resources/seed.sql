INSERT INTO USERS (username, email, password, role) VALUES ('administrator', 'admin@test.com', 'password123', 'admin');
INSERT INTO USERS (username, email, password, role) VALUES ('user', 'user@test.com', 'password123', 'user');
INSERT INTO PROJECTS (name, description) VALUES ('Project 1', 'This is the first project');
INSERT INTO PROJECTS (name, description) VALUES ('Project 2', 'This is the second project');
INSERT INTO ENTRIES (projectid, date, hours, description) VALUES (1, '2019-01-01', 2, 'This is the first entry');