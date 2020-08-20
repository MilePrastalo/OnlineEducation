INSERT INTO teacher (id, confirmed,name, password, email) VALUES ('100', 1, 'Ucitelj', '$2a$10$PxvKsblywCsPRpmccc2Id.Vf5bMDvXfRMxUhmhhAL1gxXLcWDAhIa', 'teacher@teacher.com');
INSERT INTO school (`id`, `confirmed`, `email`, `name`, `password`) VALUES ('101', 1, 'school@school.com', 'awesome school', '$2a$10$PxvKsblywCsPRpmccc2Id.Vf5bMDvXfRMxUhmhhAL1gxXLcWDAhIa');
INSERT INTO student (`id`, `confirmed`, `email`, `name`, `password`) VALUES ('102', 1, 'student@student.com', 'student ime', '$2a$10$PxvKsblywCsPRpmccc2Id.Vf5bMDvXfRMxUhmhhAL1gxXLcWDAhIa');

INSERT INTO teacher_schools (`teachers_id`, `schools_id`) VALUES ('100', '101');

INSERT INTO course (`id`, `begins_date`, `descr`, `ends_date`, `free_to_join`, `is_archivated`, `name`, `school_id`) VALUES ('102', '2020-08-14 00:00:00.000000', 'course description', '2020-08-16 00:00:00.000000', 1, 0, 'Course unique name', '101');
INSERT INTO teacher_courses (`teachers_id`, `courses_id`) VALUES ('100', '102');
INSERT INTO lesson (`id`, `name`, `course_id`) VALUES ('100', 'Lesson1', '102');
INSERT INTO course_lessons (`course_id`, `lessons_id`) VALUES ('102', '100');

INSERT INTO privilege (id, name) VALUES ('101', 'CREATE_COURSE');
INSERT INTO privilege (id, name) VALUES ('102', 'READ_COURSE');
/* Insert Roles */
INSERT INTO role (id, name) VALUES ('104', 'STUDENT');
INSERT INTO role (id, name) VALUES ('105', 'TEACHER');
INSERT INTO role (id, name) VALUES ('106', 'SCHOOL');
