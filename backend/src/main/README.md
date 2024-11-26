## Purpose of packages

### model

- Contains the tables for the database.  
- Hibernate should automatically create them, 
  overriding the schema.sql file.

### repository

- Holds SQL prepared statement for each table

### service

- Holds function that use the SQL prepared statements.

### controller

- Holds the API endpoints

### utils

- Holds utility classes/functions

### configuration

- Some configurations for the project

## Purpose of resource files

### data.sql

- Automatically populates the database with data.

## application.properties

- Hold some environment variables for the project