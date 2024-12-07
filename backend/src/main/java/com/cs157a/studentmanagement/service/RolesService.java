package com.cs157a.studentmanagement.service;

import com.cs157a.studentmanagement.dao.RolesDao;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

   private final RolesDao rolesDao;

   public RolesService(RolesDao rolesDao) {
      this.rolesDao = rolesDao;
   }

   public Integer getRoleId(String roleName) {
      return rolesDao.getRoleId(roleName);
   }

   public String getRoleName(Integer roleId) {
      return rolesDao.getRoleName(roleId);
   }

}
