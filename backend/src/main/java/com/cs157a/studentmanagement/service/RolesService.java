package com.cs157a.studentmanagement.service;

import com.cs157a.studentmanagement.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

   @Autowired
   private RolesRepository rolesRepository;

   public Short getRoleId(String roleName) {
      return rolesRepository.getRoleId(roleName);
   }

   public String getRoleName(Short roleId) {
      return rolesRepository.getRoleName(roleId);
   }

}
