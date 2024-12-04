--
-- Populate the database with test data
--

--
-- Add the roles
--

-- postgreSQL Autoincrement is 1 indexed
INSERT INTO roles (role_name) VALUES ('STUDENT') ON CONFLICT (role_name) DO NOTHING;    -- id = 1
INSERT INTO roles (role_name) VALUES ('INSTRUCTOR') ON CONFLICT (role_name) DO NOTHING; -- id = 2
