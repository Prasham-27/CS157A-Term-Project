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
-- Add Students (All test passwords are 'password123')
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

INSERT INTO students(student_id, major_id, gpa, academic_year) VALUES
(
    1,
    1, -- COMPUTER SCIENCE
    4.0, -- gpa
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

INSERT INTO students(student_id, major_id, gpa, academic_year) VALUES
(
    2,
    2, -- MECHANICAL ENGINEERING
    3.8, -- gpa
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

INSERT INTO students(student_id, major_id, gpa, academic_year) VALUES
(
    3,
    3, -- CIVIL ENGINEERING
    3.2, -- gpa
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

INSERT INTO students(student_id, major_id, gpa, academic_year) VALUES
(
    4,
    4, -- ELECTRICAL ENGINEERING
    3.5, -- gpa
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

INSERT INTO students(student_id, major_id, gpa, academic_year) VALUES
(
    5,
    5, -- BIOLOGY
    3.7, -- gpa
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

INSERT INTO students(student_id, major_id, gpa, academic_year) VALUES
(
    6,
    6, -- CHEMISTRY
    2.8, -- gpa
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

INSERT INTO students(student_id, major_id, gpa, academic_year) VALUES
(
    7,
    7, -- PHYSICS
    2.4, -- gpa
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

INSERT INTO students(student_id, major_id, gpa, academic_year) VALUES
(
    8,
    8, -- PSYCHOLOGY
    3.9, -- gpa
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

INSERT INTO students(student_id, major_id, gpa, academic_year) VALUES
(
    9,
    9, -- ECONOMICS
    3.3, -- gpa
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

INSERT INTO students(student_id, major_id, gpa, academic_year) VALUES
(
    10,
    10, -- BUSINESS ADMINISTRATION
    3.1, -- gpa
    'FRESHMAN'
) ON CONFLICT (student_id) DO NOTHING;

--
-- Add Instructors
--

-- Instructor, with id = 11
INSERT INTO users (email, first_name, last_name, password, role_id) VALUES
(
    'joseph.denims@myschool.edu',
    'Joseph',
    'Denims',
    '$2a$10$1.OaepYVSrafQGHYEpoE6.WenvdGpRuk1ZvSp2/3.u7cJ5RXwtY7W',
    2
) ON CONFLICT (email) DO NOTHING;

INSERT INTO instructors (instructor_id) VALUES
(
    11
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

INSERT INTO instructors (instructor_id) VALUES
(
    12
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

INSERT INTO instructors (instructor_id) VALUES
(
    13
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

INSERT INTO instructors (instructor_id) VALUES
(
    14
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

INSERT INTO instructors (instructor_id) VALUES
(
    15
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

INSERT INTO instructors (instructor_id) VALUES
(
    16
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

INSERT INTO instructors (instructor_id) VALUES
(
    17
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

INSERT INTO instructors (instructor_id) VALUES
(
    18
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

INSERT INTO instructors (instructor_id) VALUES
(
    19
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

INSERT INTO instructors (instructor_id) VALUES
(
    20
) ON CONFLICT (instructor_id) DO NOTHING;

