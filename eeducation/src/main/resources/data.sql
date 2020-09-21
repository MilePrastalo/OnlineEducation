INSERT INTO teacher (id, confirmed,name, password, email) VALUES ('100', 1, 'Ucitelj', '$2a$10$PxvKsblywCsPRpmccc2Id.Vf5bMDvXfRMxUhmhhAL1gxXLcWDAhIa', 'teacher@teacher.com');
INSERT INTO school (`id`, `confirmed`, `email`, `name`, `password`) VALUES ('101', 1, 'school@school.com', 'awesome school', '$2a$10$PxvKsblywCsPRpmccc2Id.Vf5bMDvXfRMxUhmhhAL1gxXLcWDAhIa');
INSERT INTO student (`id`, `confirmed`, `email`, `name`, `password`) VALUES ('102', 1, 'student@student.com', 'student ime', '$2a$10$PxvKsblywCsPRpmccc2Id.Vf5bMDvXfRMxUhmhhAL1gxXLcWDAhIa');

INSERT INTO teacher_schools (`teachers_id`, `schools_id`) VALUES ('100', '101');

INSERT INTO course (`id`, `begins_date`, `descr`, `ends_date`, `free_to_join`, `is_archivated`, `name`, `school_id`) VALUES ('102', '2020-08-14 00:00:00.000000', 'course description', '2020-08-16 00:00:00.000000', 1, 0, 'Course unique name', '101');
INSERT INTO teacher_courses (`teachers_id`, `courses_id`) VALUES ('100', '102');
INSERT INTO lesson (`id`, `name`, `course_id`) VALUES ('100', 'Lesson1', '102');
INSERT INTO course_lessons (`course_id`, `lessons_id`) VALUES ('102', '100');
INSERT INTO student_courses (`students_id`, `courses_id`) VALUES ('102', '102');

INSERT INTO privilege (id, name) VALUES ('101', 'CREATE_COURSE');
INSERT INTO privilege (id, name) VALUES ('102', 'READ_COURSE');
/* Insert Roles */
INSERT INTO role (id, name) VALUES ('104', 'STUDENT');
INSERT INTO role (id, name) VALUES ('105', 'TEACHER');
INSERT INTO role (id, name) VALUES ('106', 'SCHOOL');

INSERT INTO user_roles (users_id,roles_id) values (100,105);
INSERT INTO user_roles (users_id,roles_id) values (101,106);
INSERT INTO user_roles (users_id,roles_id) values (102,104);

INSERT INTO question (`id`, `name`, `question_type`) VALUES ('100', 'Java je', 'MULTIPLE_CHOICE');
INSERT INTO question (`id`, `name`, `question_type`) VALUES ('101', 'Broj deljiv sa 2 je', 'CHECKBOXES');
INSERT INTO question (`id`, `name`, `question_type`) VALUES ('102', '2+2=', 'SHORT_ANSWER');
INSERT INTO question (`id`, `name`, `question_type`) VALUES ('103', 'Sta je smisao zivota', 'PARAGRAPH');

INSERT INTO answer (`id`, `answer_text`, `correct`) VALUES ('100', 'Programski jezik', 1);
INSERT INTO answer (`id`, `answer_text`, `correct`) VALUES ('101', 'Cokolada', 1);
INSERT INTO answer (`id`, `answer_text`, `correct`) VALUES ('102', '1', 0);
INSERT INTO answer (`id`, `answer_text`, `correct`) VALUES ('103', '2',1);
INSERT INTO answer (`id`, `answer_text`, `correct`) VALUES ('104', '3', 0);

INSERT INTO question_answer (`question_id`, `answer_id`) VALUES ('100', '100');
INSERT INTO question_answer (`question_id`, `answer_id`) VALUES ('100', '101');
INSERT INTO question_answer (`question_id`, `answer_id`) VALUES ('101', '102');
INSERT INTO question_answer (`question_id`, `answer_id`) VALUES ('101', '103');
INSERT INTO question_answer (`question_id`, `answer_id`) VALUES ('101', '104');

INSERT INTO test (`id`, `date`, `duration`, `name`, `test_type`, `course_id`) VALUES ('100', '2020-09-22 17:50:00.000000', '30', 'Kolokvijum 1', 'SELF_GRADING', '102');
INSERT INTO test_questions (`test_id`, `questions_id`) VALUES ('100', '100');
INSERT INTO test_questions (`test_id`, `questions_id`) VALUES ('100', '101');
INSERT INTO test_questions (`test_id`, `questions_id`) VALUES ('100', '102');
INSERT INTO test_questions (`test_id`, `questions_id`) VALUES ('100', '103');

INSERT INTO course_tests (`course_id`, `tests_id`) VALUES ('102', '100');
