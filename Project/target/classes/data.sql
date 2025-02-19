CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    starting_at TIMESTAMP,
    ending_at TIMESTAMP DEFAULT NULL,
    active_project_days INT DEFAULT 0
);

CREATE TABLE person(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    title VARCHAR(255)
);

CREATE TABLE personprojectposition(
    id SERIAL PRIMARY KEY,
    start_in_project TIMESTAMP DEFAULT NULL,
    end_in_project TIMESTAMP DEFAULT NULL,
    days_active INT DEFAULT 0,
    person_id INT,
    project_id INT
);

INSERT INTO project ( title, description, starting_at, ending_at, active_project_days)VALUES ('Bmw-Projekt', 'Auto Konfiguration in der App', '2022-09-08 12:00:00', '2025-09-08 12:00:00', 0);
INSERT INTO project ( title, description, starting_at, ending_at, active_project_days)VALUES ('Barmer', 'Online LogIn Webapplication', '2022-01-08 00:00:00', '2024-09-08 12:00:00', 9);
INSERT INTO project ( title, description, starting_at) VALUES ( 'Daimler', 'Eine application wird erstellt', '2024-05-03 12:00:00');
INSERT INTO person ( name, title) VALUES ( 'Tessa Test', 'Software Engineer');
INSERT INTO person ( name, title) VALUES ( 'Luca Lucky', 'Projekt Manager');
INSERT INTO person ( name, title) VALUES ( 'Mina Minestrone', 'Senior Software Engineer');
INSERT INTO personprojectposition (start_in_project, days_active, person_id, project_id) VALUES ('2022-09-08 12:00:00' , 90, 1,1 );
INSERT INTO personprojectposition (start_in_project, days_active, person_id, project_id) VALUES ('2023-09-08 12:00:00',2, 2,1 );
INSERT INTO personprojectposition (person_id, days_active, project_id) VALUES ( 3,56, 2);
INSERT INTO personprojectposition (person_id, days_active, project_id) VALUES ( 3,33, 1)