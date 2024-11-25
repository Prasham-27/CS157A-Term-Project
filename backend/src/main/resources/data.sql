--
-- Populate the database with test data
--

--
-- Add the users
--

-- TODO Revoke the ALL privileged on admin later
--CREATE USER admin WITH PASSWORD 'admin';
--GRANT ALL PRIVILEGES ON DATABASE studentmanagement TO admin;
--GRANT CREATEROLE TO admin;

-- TODO make login default in the DataSourceConfig later
--CREATE USER login WITH PASSWORD 'login';
--GRANT SELECT ON TABLE users TO login;

-- TODO Change these privledges later for both users
--CREATE USER student WITH PASSWORD 'student';
--GRANT ALL PRIVILEGES ON TABLE users TO student;

--CREATE USER instructor WITH PASSWORD 'instructor';
--GRANT ALL PRIVILEGES ON TABLE users TO instructor;

--
-- Add the roles
--

INSERT INTO roles (role_name) VALUES ('STUDENT') ON CONFLICT (role_name) DO NOTHING;    -- id = 0
INSERT INTO roles (role_name) VALUES ('INSTRUCTOR') ON CONFLICT (role_name) DO NOTHING; -- id = 1

--
-- Revoke SUPERUSER priviledge from admin
--
--ALTER USER admin WITH NOCREATEROLE
--ALTER USER admin WITH NOSUPERUSER;