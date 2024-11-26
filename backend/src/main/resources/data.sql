--
-- Populate the database with test data
--

--
-- Add the roles
--

INSERT INTO roles (role_name) VALUES ('STUDENT') ON CONFLICT (role_name) DO NOTHING;    -- id = 0
INSERT INTO roles (role_name) VALUES ('INSTRUCTOR') ON CONFLICT (role_name) DO NOTHING; -- id = 1
