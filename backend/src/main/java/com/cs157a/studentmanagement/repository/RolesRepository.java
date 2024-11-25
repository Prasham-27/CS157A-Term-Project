package com.cs157a.studentmanagement.repository;

import com.cs157a.studentmanagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Users, Long>  {

   @Query(value = "SELECT role_name FROM roles WHERE role_id = :roleId", nativeQuery = true)
   String getRoleName(Short roleId);

   @Query(value = "SELECT role_id FROM roles WHERE role_name = :roleName", nativeQuery = true)
   Short getRoleId(String roleName);

}
