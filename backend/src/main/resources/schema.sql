-- Create Type enums --
DO
'
DECLARE
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = ''days'') THEN
        CREATE TYPE DAYS AS ENUM (''M'', ''T'', ''W'', ''TH'', ''F'', ''SAT'', ''SUN'');
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = ''enrollment_status'') THEN
        CREATE TYPE ENROLLMENT_STATUS AS ENUM (''ENROLLED'', ''DROPPED'', ''COMPLETED'');
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = ''grades'') THEN
        CREATE TYPE GRADES AS ENUM (''A'', ''A-'', ''B+'', ''B'', ''B-'', ''C+'', ''C'', ''C-'', ''D'', ''F'', ''N/A'');
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = ''academic_year'') THEN
        CREATE TYPE ACADEMIC_YEAR AS ENUM(''FRESHMAN'', ''SOPHOMORE'', ''JUNIOR'', ''SENIOR'');
    END IF;
END;
' LANGUAGE PLPGSQL;


-- Create the tables --
CREATE TABLE IF NOT EXISTS roles (
  role_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  role_name VARCHAR(40) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
  user_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  email VARCHAR(60) UNIQUE NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NOT NULL,
  password VARCHAR(60) NOT NULL,
  role_id INTEGER NOT NULL CONSTRAINT role REFERENCES roles(role_id)
);

CREATE TABLE IF NOT EXISTS majors (
  major_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  major_name VARCHAR(40) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
  student_id INTEGER UNIQUE CONSTRAINT student_id REFERENCES users(user_id),
  major_id INTEGER NOT NULL CONSTRAINT major_id REFERENCES majors(major_id),
  academic_year ACADEMIC_YEAR NOT NULL
);

CREATE TABLE IF NOT EXISTS departments (
  dept_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  dept_name VARCHAR(40) UNIQUE NOT NULL,
  dept_abbreviation VARCHAR(5) UNIQUE NOT NULL
);

-- Each instructor will only be in one dept for now
CREATE TABLE IF NOT EXISTS instructors (
  instructor_id INTEGER UNIQUE CONSTRAINT instructor_id REFERENCES users(user_id),
  dept_id INTEGER CONSTRAINT dept_id REFERENCES departments(dept_id)
);

CREATE TABLE IF NOT EXISTS departments_to_majors (
  dept_id INTEGER CONSTRAINT dept_id REFERENCES departments(dept_id),
  major_id INTEGER NOT NULL CONSTRAINT major_id REFERENCES majors(major_id),
  UNIQUE(dept_id, major_id)
);

CREATE TABLE IF NOT EXISTS courses (
  course_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  dept_id INTEGER NOT NULL CONSTRAINT dept_id REFERENCES departments(dept_id),
  course_num INTEGER NOT NULL,
  UNIQUE(dept_id, course_num)
);

CREATE TABLE IF NOT EXISTS course_info (
  course_id INTEGER UNIQUE NOT NULL CONSTRAINT course_id REFERENCES courses(course_id),
  course_name VARCHAR(40) NOT NULL,
  points REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS instructor_to_courses (
  instructor_course_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  instructor_id INTEGER NOT NULL CONSTRAINT instructor_id REFERENCES instructors(instructor_id),
  course_id INTEGER NOT NULL CONSTRAINT course_id REFERENCES courses(course_id),
  max_enrollment INTEGER NOT NULL,
  num_enrolled INTEGER NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  UNIQUE (instructor_id, course_id)
);

CREATE TABLE IF NOT EXISTS enrollments (
  instructor_course_id INTEGER NOT NULL CONSTRAINT instructor_course_id REFERENCES instructor_to_courses(instructor_course_id),
  student_id INTEGER CONSTRAINT student_id REFERENCES students(student_id),
  grade GRADES NOT NULL,
  enrollment_date TIMESTAMP NOT NULL,
  status ENROLLMENT_STATUS NOT NULL,
  PRIMARY KEY (student_id, instructor_course_id)
);

CREATE TABLE IF NOT EXISTS instructor_to_courses_days (
  instructor_course_id INTEGER NOT NULL CONSTRAINT instructor_course_id REFERENCES instructor_to_courses(instructor_course_id),
  "day" DAYS NOT NULL,
  PRIMARY KEY (instructor_course_id, "day")
);




