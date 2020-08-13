INSERT INTO teacher (id, confirmed,name, password, email) VALUES ('100', 1, 'Ucitelj', '$2a$10$PxvKsblywCsPRpmccc2Id.Vf5bMDvXfRMxUhmhhAL1gxXLcWDAhIa', 'teacher@teacher.com');
INSERT INTO school (`id`, `confirmed`, `email`, `name`, `password`) VALUES ('101', 1, 'school@school.com', 'awesome school', '$10$PxvKsblywCsPRpmccc2Id.Vf5bMDvXfRMxUhmhhAL1gxXLcWDAhIa');

INSERT INTO teacher_schools (`teachers_id`, `schools_id`) VALUES ('100', '101');


INSERT INTO privilege (id, name) VALUES ('101', 'CREATE_COURSE');
INSERT INTO privilege (id, name) VALUES ('102', 'READ_COURSE');
/* Insert Roles */
INSERT INTO role (id, name) VALUES ('104', 'STUDENT');
INSERT INTO role (id, name) VALUES ('105', 'TEACHER');
INSERT INTO role (id, name) VALUES ('106', 'SCHOOL');
