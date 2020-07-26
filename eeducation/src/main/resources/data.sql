INSERT INTO teacher (id, confirmed, password, email) VALUES ('100', 1, '$2a$10$PxvKsblywCsPRpmccc2Id.Vf5bMDvXfRMxUhmhhAL1gxXLcWDAhIa', 'teacher@teacher.com');
INSERT INTO privilege (id, name) VALUES ('101', 'CREATE_COURSE');
INSERT INTO privilege (id, name) VALUES ('102', 'READ_COURSE');
/* Insert Roles */
INSERT INTO role (id, name) VALUES ('104', 'STUDENT');
INSERT INTO role (id, name) VALUES ('105', 'TEACHER');
INSERT INTO role (id, name) VALUES ('106', 'SCHOOL');
