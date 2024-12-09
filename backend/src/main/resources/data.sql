--
-- Populate the database with test data
--

--
-- Add the roles
--

-- postgreSQL Autoincrement is 1 indexed
INSERT INTO roles (role_name) VALUES ('STUDENT') ON CONFLICT (role_name) DO NOTHING;    -- id = 1
INSERT INTO roles (role_name) VALUES ('INSTRUCTOR') ON CONFLICT (role_name) DO NOTHING; -- id = 2


--
-- Add Majors
--

INSERT INTO majors (major_name) VALUES ('COMPUTER SCIENCE') ON CONFLICT (major_name) DO NOTHING; -- Major, id = 1
INSERT INTO majors (major_name) VALUES ('MECHANICAL ENGINEERING') ON CONFLICT (major_name) DO NOTHING; -- Major, id = 2
INSERT INTO majors (major_name) VALUES ('CIVIL ENGINEERING') ON CONFLICT (major_name) DO NOTHING; -- Major, id = 3
INSERT INTO majors (major_name) VALUES ('ELECTRICAL ENGINEERING') ON CONFLICT (major_name) DO NOTHING; -- Major, id = 4
INSERT INTO majors (major_name) VALUES ('BIOLOGY') ON CONFLICT (major_name) DO NOTHING; -- Major, id = 5
INSERT INTO majors (major_name) VALUES ('CHEMISTRY') ON CONFLICT (major_name) DO NOTHING; -- Major, id = 6
INSERT INTO majors (major_name) VALUES ('PHYSICS') ON CONFLICT (major_name) DO NOTHING; -- Major, id = 7
INSERT INTO majors (major_name) VALUES ('PSYCHOLOGY') ON CONFLICT (major_name) DO NOTHING; -- Major, id = 8
INSERT INTO majors (major_name) VALUES ('ECONOMICS') ON CONFLICT (major_name) DO NOTHING; -- Major, id = 9
INSERT INTO majors (major_name) VALUES ('BUSINESS ADMINISTRATION') ON CONFLICT (major_name) DO NOTHING; -- Major, id = 10

--
-- Add Departments
--
INSERT INTO departments (dept_name, dept_abbreviation)
VALUES ('COMPUTER SCIENCE', 'CS') ON CONFLICT (dept_name) DO NOTHING; -- Dept, id = 1
INSERT INTO departments (dept_name, dept_abbreviation)
VALUES ('MECHANICAL ENGINEERING', 'ME') ON CONFLICT (dept_name) DO NOTHING; -- Dept, id = 2
INSERT INTO departments (dept_name, dept_abbreviation)
VALUES ('CIVIL ENGINEERING', 'CE') ON CONFLICT (dept_name) DO NOTHING; -- Dept, id = 3
INSERT INTO departments (dept_name, dept_abbreviation)
VALUES ('ELECTRICAL ENGINEERING', 'EE') ON CONFLICT (dept_name) DO NOTHING; -- Dept, id = 4
INSERT INTO departments (dept_name, dept_abbreviation)
VALUES ('BIOLOGY', 'BIO') ON CONFLICT (dept_name) DO NOTHING; -- Dept, id = 5
INSERT INTO departments (dept_name, dept_abbreviation)
VALUES ('CHEMISTRY', 'CHEM') ON CONFLICT (dept_name) DO NOTHING; -- Dept, id = 6
INSERT INTO departments (dept_name, dept_abbreviation)
VALUES ('PHYSICS', 'PHYS') ON CONFLICT (dept_name) DO NOTHING; -- Dept, id = 7
INSERT INTO departments (dept_name, dept_abbreviation)
VALUES ('PSYCHOLOGY', 'PSY') ON CONFLICT (dept_name) DO NOTHING; -- Dept, id = 8
INSERT INTO departments (dept_name, dept_abbreviation)
VALUES ('ECONOMICS', 'ECON') ON CONFLICT (dept_name) DO NOTHING; -- Dept, id = 9
INSERT INTO departments (dept_name, dept_abbreviation)
VALUES ('BUSINESS ADMINISTRATION', 'BA') ON CONFLICT (dept_name) DO NOTHING; -- Dept, id = 10

--
-- Associate Departments and majors
--
INSERT INTO departments_to_majors (dept_id, major_id) VALUES (1, 1) ON CONFLICT DO NOTHING; -- Computer Science
INSERT INTO departments_to_majors (dept_id, major_id) VALUES (2, 2) ON CONFLICT DO NOTHING; -- Mechanical Engineering
INSERT INTO departments_to_majors (dept_id, major_id) VALUES (3, 3) ON CONFLICT DO NOTHING; -- Civil Engineering
INSERT INTO departments_to_majors (dept_id, major_id) VALUES (4, 4) ON CONFLICT DO NOTHING; -- Electrical Engineering
INSERT INTO departments_to_majors (dept_id, major_id) VALUES (5, 5) ON CONFLICT DO NOTHING; -- Biology
INSERT INTO departments_to_majors (dept_id, major_id) VALUES (6, 6) ON CONFLICT DO NOTHING; -- Chemistry
INSERT INTO departments_to_majors (dept_id, major_id) VALUES (7, 7) ON CONFLICT DO NOTHING; -- Physics
INSERT INTO departments_to_majors (dept_id, major_id) VALUES (8, 8) ON CONFLICT DO NOTHING; -- Psychology
INSERT INTO departments_to_majors (dept_id, major_id) VALUES (9, 9) ON CONFLICT DO NOTHING; -- Economics
INSERT INTO departments_to_majors (dept_id, major_id) VALUES (10, 10) ON CONFLICT DO NOTHING; -- Business Administration


--
-- Add Students (All test passwords are 'password123' for simple testing)
--


-- Student, with id = 1
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'john.doe@myschool.edu',
    'John',
    'Doe',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    1
) ON CONFLICT (email) DO NOTHING;

INSERT INTO students(student_id, major_id, academic_year) VALUES
(
    1,
    1, -- COMPUTER SCIENCE
    'FRESHMAN'
) ON CONFLICT (student_id) DO NOTHING;

-- Student, with id = 2
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'jane.doe@myschool.edu ',
    'Jane',
    'Doe',
    '$2a$10$IrboDwEV1aBvuMJYQSXq4eARMHh5tveXvxUmPpkzQM7wJvuUMsyTW ',
    1
) ON CONFLICT (email) DO NOTHING;

INSERT INTO students(student_id, major_id, academic_year) VALUES
(
    2,
    2, -- MECHANICAL ENGINEERING
    'SOPHOMORE'
) ON CONFLICT (student_id) DO NOTHING;

-- Student, with id = 3
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'michael.smith@myschool.edu',
    'Michael',
    'Smith',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    1
) ON CONFLICT (email) DO NOTHING;

INSERT INTO students(student_id, major_id, academic_year) VALUES
(
    3,
    3, -- CIVIL ENGINEERING
    'JUNIOR'
) ON CONFLICT (student_id) DO NOTHING;

-- Student, with id = 4
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'susan.johnson@myschool.edu',
    'Susan',
    'Johnson',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    1
) ON CONFLICT (email) DO NOTHING;

INSERT INTO students(student_id, major_id, academic_year) VALUES
(
    4,
    4, -- ELECTRICAL ENGINEERING
    'SENIOR'
) ON CONFLICT (student_id) DO NOTHING;

-- Student, with id = 5
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'david.williams@myschool.edu',
    'David',
    'Williams',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    1
) ON CONFLICT (email) DO NOTHING;

INSERT INTO students(student_id, major_id, academic_year) VALUES
(
    5,
    5, -- BIOLOGY
    'SOPHOMORE'
) ON CONFLICT (student_id) DO NOTHING;

-- Student, with id = 6
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'lisa.brown@myschool.edu',
    'Lisa',
    'Brown',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    1
) ON CONFLICT (email) DO NOTHING;

INSERT INTO students(student_id, major_id, academic_year) VALUES
(
    6,
    6, -- CHEMISTRY
    'FRESHMAN'
) ON CONFLICT (student_id) DO NOTHING;

-- Student, with id = 7
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'robert.martin@myschool.edu',
    'Robert',
    'Martin',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    1
) ON CONFLICT (email) DO NOTHING;

INSERT INTO students(student_id, major_id, academic_year) VALUES
(
    7,
    7, -- PHYSICS
    'JUNIOR'
) ON CONFLICT (student_id) DO NOTHING;

-- Student, with id = 8
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'mary.moore@myschool.edu',
    'Mary',
    'Moore',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    1
) ON CONFLICT (email) DO NOTHING;

INSERT INTO students(student_id, major_id, academic_year) VALUES
(
    8,
    8, -- PSYCHOLOGY
    'SENIOR'
) ON CONFLICT (student_id) DO NOTHING;

-- Student, with id = 9
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'james.taylor@myschool.edu',
    'James',
    'Taylor',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    1
) ON CONFLICT (email) DO NOTHING;

INSERT INTO students(student_id, major_id, academic_year) VALUES
(
    9,
    9, -- ECONOMICS
    'SOPHOMORE'
) ON CONFLICT (student_id) DO NOTHING;

-- Student, with id = 10
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'patricia.garcia@myschool.edu',
    'Patricia',
    'Garcia',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    1
) ON CONFLICT (email) DO NOTHING;

INSERT INTO students(student_id, major_id, academic_year) VALUES
(
    10,
    10, -- BUSINESS ADMINISTRATION
    'FRESHMAN'
) ON CONFLICT (student_id) DO NOTHING;


--
-- Add Instructors
--


-- Computer Science


-- Instructor, with id = 11
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'joseph.denims@myschool.edu',
    'Joseph',
    'Denims',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    11, 1 -- Computer Science
) ON CONFLICT (instructor_id) DO NOTHING;

-- Instructor, with id = 12
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'andrew.white@myschool.edu',
    'Andrew',
    'White',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    12, 2 -- Mechanical Engineering
) ON CONFLICT (instructor_id) DO NOTHING;

-- Instructor, with id = 13
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'sarah.martin@myschool.edu',
    'Sarah',
    'Martin',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    13, 3 -- Civil Engineering
) ON CONFLICT (instructor_id) DO NOTHING;


-- Instructor, with id = 14
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'charles.brown@myschool.edu',
    'Charles',
    'Brown',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    14, 4 -- Electrical Engineering
) ON CONFLICT (instructor_id) DO NOTHING;

-- Instructor, with id = 15
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'emma.davis@myschool.edu',
    'Emma',
    'Davis',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    15, 5 -- Biology
) ON CONFLICT (instructor_id) DO NOTHING;

-- Instructor, with id = 16
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'daniel.clark@myschool.edu',
    'Daniel',
    'Clark',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    16, 6 -- Chemistry
) ON CONFLICT (instructor_id) DO NOTHING;

-- Instructor, with id = 17
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'olivia.wilson@myschool.edu',
    'Olivia',
    'Wilson',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    17, 7 -- Physics
) ON CONFLICT (instructor_id) DO NOTHING;

-- Instructor, with id = 18
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'michael.moore@myschool.edu',
    'Michael',
    'Moore',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    18, 8 -- Psychology
) ON CONFLICT (instructor_id) DO NOTHING;

-- Instructor, with id = 19
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'lucas.miller@myschool.edu',
    'Lucas',
    'Miller',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    19, 9 -- Economics
) ON CONFLICT (instructor_id) DO NOTHING;

-- Instructor, with id = 20
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'isabella.harris@myschool.edu',
    'Isabella',
    'Harris',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    20, 10 -- Business Administration
) ON CONFLICT (instructor_id) DO NOTHING;


-- Computer Science, instuctor_id = 21
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'alice.thompson@myschool.edu',
    'Alice',
    'Thompson',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    21, 1
) ON CONFLICT (instructor_id) DO NOTHING;

-- Mechanical Engineering, instuctor_id = 22
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'benjamin.smith@myschool.edu',
    'Benjamin',
    'Smith',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    22, 2
) ON CONFLICT (instructor_id) DO NOTHING;

-- Civil Engineering, instructor_id = 23
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'carla.jones@myschool.edu',
    'Carla',
    'Jones',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    23, 3
) ON CONFLICT (instructor_id) DO NOTHING;

-- Electrical Engineering, instructor_id = 24
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'david.johnson@myschool.edu',
    'David',
    'Johnson',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id, dept_id) VALUES
(
    24, 4
) ON CONFLICT (instructor_id) DO NOTHING;



--
-- Add courses
--


-- Courses for Computer Science
INSERT INTO courses (dept_id, course_num) VALUES (1, 101) ON CONFLICT DO NOTHING; -- CS 101
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 1 AND course_num = 101), 'Introduction to Computer Science', 3.0)
ON CONFLICT DO NOTHING;

INSERT INTO courses (dept_id, course_num) VALUES (1, 201) ON CONFLICT DO NOTHING; -- CS 201
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 1 AND course_num = 201), 'Data Structures', 3.5)
ON CONFLICT DO NOTHING;

-- Courses for Mechanical Engineering
INSERT INTO courses (dept_id, course_num) VALUES (2, 101) ON CONFLICT DO NOTHING; -- ME 101
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 2 AND course_num = 101), 'Introduction to Mechanical Engineering', 3.0)
ON CONFLICT DO NOTHING;

INSERT INTO courses (dept_id, course_num) VALUES (2, 201) ON CONFLICT DO NOTHING; -- ME 201
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 2 AND course_num = 201), 'Thermodynamics', 3.5)
ON CONFLICT DO NOTHING;

-- Courses for Civil Engineering
INSERT INTO courses (dept_id, course_num) VALUES (3, 101) ON CONFLICT DO NOTHING; -- CE 101
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 3 AND course_num = 101), 'Introduction to Civil Engineering', 3.0)
ON CONFLICT DO NOTHING;

INSERT INTO courses (dept_id, course_num) VALUES (3, 201) ON CONFLICT DO NOTHING; -- CE 201
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 3 AND course_num = 201), 'Structural Analysis', 3.5)
ON CONFLICT DO NOTHING;

-- Courses for Electrical Engineering
INSERT INTO courses (dept_id, course_num) VALUES (4, 101) ON CONFLICT DO NOTHING; -- EE 101
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 4 AND course_num = 101), 'Introduction to Electrical Engineering', 3.0)
ON CONFLICT DO NOTHING;

INSERT INTO courses (dept_id, course_num) VALUES (4, 201) ON CONFLICT DO NOTHING; -- EE 201
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 4 AND course_num = 201), 'Circuit Design', 3.5)
ON CONFLICT DO NOTHING;

-- Courses for Biology
INSERT INTO courses (dept_id, course_num) VALUES (5, 101) ON CONFLICT DO NOTHING; -- BIO 101
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 5 AND course_num = 101), 'Introduction to Biology', 3.0)
ON CONFLICT DO NOTHING;

-- Courses for Chemistry
INSERT INTO courses (dept_id, course_num) VALUES (6, 101) ON CONFLICT DO NOTHING; -- CHEM 101
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 6 AND course_num = 101), 'Introduction to Chemistry', 3.0)
ON CONFLICT DO NOTHING;

-- Courses for Physics
INSERT INTO courses (dept_id, course_num) VALUES (7, 101) ON CONFLICT DO NOTHING; -- PHYS 101
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 7 AND course_num = 101), 'Introduction to Physics', 3.0)
ON CONFLICT DO NOTHING;

-- Courses for Psychology
INSERT INTO courses (dept_id, course_num) VALUES (8, 101) ON CONFLICT DO NOTHING; -- PSY 101
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 8 AND course_num = 101), 'Introduction to Psychology', 3.0)
ON CONFLICT DO NOTHING;

-- Courses for Economics
INSERT INTO courses (dept_id, course_num) VALUES (9, 101) ON CONFLICT DO NOTHING; -- ECON 101
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 9 AND course_num = 101), 'Introduction to Economics', 3.0)
ON CONFLICT DO NOTHING;

-- Courses for Business Administration
INSERT INTO courses (dept_id, course_num) VALUES (10, 101) ON CONFLICT DO NOTHING; -- BA 101
INSERT INTO course_info (course_id, course_name, points)
VALUES ((SELECT course_id FROM courses WHERE dept_id = 10 AND course_num = 101), 'Introduction to Business Administration', 3.0)
ON CONFLICT DO NOTHING;


--
-- Assign courses to instructors
--

-- Assign instructors to courses for Computer Science
-- Instructor 11: Joseph Denims (CS)
INSERT INTO instructor_to_courses (instructor_id, course_id, max_enrollment, num_enrolled, start_time, end_time)
VALUES
(11, 1, 30, 0, '09:00', '10:15'), -- CS 101
(11, 2, 25, 0, '10:30', '11:45')  -- CS 102
ON CONFLICT DO NOTHING;

-- Assign days for Joseph Denims
INSERT INTO instructor_to_courses_days (instructor_course_id, "day")
VALUES
(1, 'M'),
(1, 'W'),
(2, 'T'),
(2, 'TH')
ON CONFLICT DO NOTHING;

-- Instructor 12: Andrew White (Mechanical Engineering)
INSERT INTO instructor_to_courses (instructor_id, course_id, max_enrollment, num_enrolled, start_time, end_time)
VALUES
(12, 3, 30, 0, '09:00', '10:15'), -- ME 101
(12, 4, 30, 0, '09:00', '10:15')  -- ME 202
ON CONFLICT DO NOTHING;

-- Assign days for Andrew White
INSERT INTO instructor_to_courses_days (instructor_course_id, "day")
VALUES
(3, 'M'),
(3, 'W'),
(4, 'T'),
(4, 'TH')
ON CONFLICT DO NOTHING;

-- Instructor 13: Sarah Martin (Civil Engineering)
INSERT INTO instructor_to_courses (instructor_id, course_id, max_enrollment, num_enrolled, start_time, end_time)
VALUES
(13, 5, 30, 0, '09:00', '10:15'), -- CE 101
(13, 6, 25, 0, '10:30', '11:45')  -- CE 102
ON CONFLICT DO NOTHING;

-- Assign days for Sarah Martin
INSERT INTO instructor_to_courses_days (instructor_course_id, "day")
VALUES
(5, 'M'),
(5, 'W'),
(6, 'T'),
(6, 'TH')
ON CONFLICT DO NOTHING;

-- Instructor 14: Charles Brown (Electrical Engineering)
INSERT INTO instructor_to_courses (instructor_id, course_id, max_enrollment, num_enrolled, start_time, end_time)
VALUES
(14, 7, 30, 0, '09:00', '10:15'), -- EE 101
(14, 8, 30, 0, '09:00', '10:15')  -- EE 102
ON CONFLICT DO NOTHING;

-- Assign days for Charles Brown
INSERT INTO instructor_to_courses_days (instructor_course_id, "day")
VALUES
(7, 'M'),
(7, 'W'),
(8, 'M'),
(8, 'W')
ON CONFLICT DO NOTHING;

-- Instructor 15: Emma Davis (Biology)
INSERT INTO instructor_to_courses (instructor_id, course_id, max_enrollment, num_enrolled, start_time, end_time)
VALUES
(15, 9, 30, 0, '09:00', '10:15')
ON CONFLICT DO NOTHING;

-- Assign days for Emma Davis
INSERT INTO instructor_to_courses_days (instructor_course_id, "day")
VALUES
(9, 'M'),
(9, 'W')
ON CONFLICT DO NOTHING;

-- Instructor 16: Daniel Clark (Chemistry)
INSERT INTO instructor_to_courses (instructor_id, course_id, max_enrollment, num_enrolled, start_time, end_time)
VALUES
(16, 10, 25, 0, '09:00', '10:15')
ON CONFLICT DO NOTHING;

-- Assign days for Daniel Clark
INSERT INTO instructor_to_courses_days (instructor_course_id, "day")
VALUES
(10, 'T'),
(10, 'TH')
ON CONFLICT DO NOTHING;

-- Instructor 17: Olivia Wilson (Physics)
INSERT INTO instructor_to_courses (instructor_id, course_id, max_enrollment, num_enrolled, start_time, end_time)
VALUES
(17, 11, 30, 0, '09:00', '10:15')
ON CONFLICT DO NOTHING;

-- Assign days for Olivia Wilson
INSERT INTO instructor_to_courses_days (instructor_course_id, "day")
VALUES
(11, 'T'),
(11, 'TH')
ON CONFLICT DO NOTHING;

-- Instructor 18: Michael Moore (Psychology)
INSERT INTO instructor_to_courses (instructor_id, course_id, max_enrollment, num_enrolled, start_time, end_time)
VALUES
(18, 12, 30, 0, '09:00', '10:15')
ON CONFLICT DO NOTHING;

-- Assign days for Michael Moore
INSERT INTO instructor_to_courses_days (instructor_course_id, "day")
VALUES
(12, 'M'),
(12, 'W')
ON CONFLICT DO NOTHING;

-- Instructor 19: Lucas Miller (Economics)
INSERT INTO instructor_to_courses (instructor_id, course_id, max_enrollment, num_enrolled, start_time, end_time)
VALUES
(19, 13, 25, 0, '09:00', '10:15')
ON CONFLICT DO NOTHING;

-- Assign days for Lucas Miller
INSERT INTO instructor_to_courses_days (instructor_course_id, "day")
VALUES
(13, 'M'),
(13, 'W')
ON CONFLICT DO NOTHING;

-- Instructor 20: Isabella Harris (Business Administration)
INSERT INTO instructor_to_courses (instructor_id, course_id, max_enrollment, num_enrolled, start_time, end_time)
VALUES
(20, 14, 30, 0, '09:00', '10:15')
ON CONFLICT DO NOTHING;

-- Assign days for Isabella Harris
INSERT INTO instructor_to_courses_days (instructor_course_id, "day")
VALUES
(14, 'T'),
(14, 'TH')
ON CONFLICT DO NOTHING;

-- Instructor 21: Alice Thompson (Computer Science)
INSERT INTO instructor_to_courses (instructor_id, course_id, max_enrollment, num_enrolled, start_time, end_time)
VALUES
(21, 1, 30, 0, '09:00', '10:15') -- CS 101
ON CONFLICT DO NOTHING;

-- Assign days for Alice Thompson
INSERT INTO instructor_to_courses_days (instructor_course_id, "day")
VALUES
(15, 'M'),
(15, 'W')
ON CONFLICT DO NOTHING;



--
-- Enroll students into courses
--

-- Enroll Student 1 into CS101 and CS201
-- CS101 (Instructor 11) - completed, grade A
-- CS201 (Instructor 11) - completed, grade A-

INSERT INTO enrollments (instructor_course_id, student_id, grade, enrollment_date, status)
VALUES
  (1, 1, 'A', CURRENT_TIMESTAMP, 'COMPLETED'),
  (2, 1, 'A-', CURRENT_TIMESTAMP, 'COMPLETED')
  ON CONFLICT DO NOTHING;

-- Enroll Student 2 into ME101
-- ME101 (Instructor 12) - completed, grade B+ for one, dropped for the other
-- Mechanical Engineering student (Jane Doe)

-- CS101 (Instructor 11) - dropped, grade N/A
-- ME101 (Instructor 12) - completed, grade B+

INSERT INTO enrollments (instructor_course_id, student_id, grade, enrollment_date, status)
VALUES
  (3, 2, 'B+', CURRENT_TIMESTAMP, 'COMPLETED'),
  (4, 2, 'N/A', CURRENT_TIMESTAMP, 'DROPPED')
  ON CONFLICT DO NOTHING;


-- Enroll Student 3 (Civil Engineering) in course 101 and 201
INSERT INTO enrollments (instructor_course_id, student_id, grade, enrollment_date, status)
VALUES
(5, 3, 'B', CURRENT_TIMESTAMP, 'COMPLETED'),
(6, 3, 'N/A', CURRENT_TIMESTAMP, 'ENROLLED')
  ON CONFLICT DO NOTHING;

-- Enroll Student 4 (Electrical Engineering) in course 101
INSERT INTO enrollments (instructor_course_id, student_id, grade, enrollment_date, status)
VALUES
(7, 4, 'A', CURRENT_TIMESTAMP, 'COMPLETED')
  ON CONFLICT DO NOTHING;

-- Enroll Student 5 (Biology) in course 101
INSERT INTO enrollments (instructor_course_id, student_id, grade, enrollment_date, status)
VALUES
(9, 5, 'A-', CURRENT_TIMESTAMP, 'COMPLETED')
  ON CONFLICT DO NOTHING;

-- Enroll Student 6 (Chemistry) in course 101
INSERT INTO enrollments (instructor_course_id, student_id, grade, enrollment_date, status)
VALUES
(10, 6, 'N/A', CURRENT_TIMESTAMP, 'ENROLLED')
  ON CONFLICT DO NOTHING;

-- Enroll Student 7 (Physics) in course 101
INSERT INTO enrollments (instructor_course_id, student_id, grade, enrollment_date, status)
VALUES
(11, 7, 'B+', CURRENT_TIMESTAMP, 'COMPLETED')
  ON CONFLICT DO NOTHING;

-- Enroll Student 8 (Psychology) in course 101
INSERT INTO enrollments (instructor_course_id, student_id, grade, enrollment_date, status)
VALUES
(12, 8, 'N/A', CURRENT_TIMESTAMP, 'ENROLLED')
  ON CONFLICT DO NOTHING;

-- Enroll Student 9 (Economics) in course 101
INSERT INTO enrollments (instructor_course_id, student_id, grade, enrollment_date, status)
VALUES
(13, 9, 'B', CURRENT_TIMESTAMP, 'COMPLETED')
  ON CONFLICT DO NOTHING;






