package com.cs157a.studentmanagement.service;

import com.cs157a.studentmanagement.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

   @Autowired
   private RolesRepository rolesRepository;

   public Integer getRoleId(String roleName) {
      return rolesRepository.getRoleId(roleName);
   }

   public String getRoleName(Integer roleId) {
      return rolesRepository.getRoleName(roleId);
   }

}
