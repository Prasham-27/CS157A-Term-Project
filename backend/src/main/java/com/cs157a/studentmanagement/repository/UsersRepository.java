package com.cs157a.studentmanagement.repository;

import com.cs157a.studentmanagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

/**
 * Defines the queries for the Users entity
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

   @Query(value = "SELECT password FROM Users WHERE user_id = :userId", nativeQuery = true)
   String getPasswordHash(Long userId);

   @Query(value = "SELECT role_id FROM Users WHERE user_id = :userId", nativeQuery = true)
   Integer getRoleId(Long userId);

   @Modifying
   @Query(value = "INSERT INTO users (email, password, first_name, last_name, role_id) " +
           "VALUES (:email, :hashedPassword, :firstName, :lastName, :role)", nativeQuery = true)
   Integer insertUser(
           String email,
           String hashedPassword,
           String firstName,
           String lastName,
           Integer role);

}