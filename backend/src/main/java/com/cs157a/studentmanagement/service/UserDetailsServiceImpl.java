package com.cs157a.studentmanagement.service;

import com.cs157a.studentmanagement.dao.UsersDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Serivce used to help log in a user
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   private final UsersDao usersDao;

   public UserDetailsServiceImpl(UsersDao usersDao) {
      this.usersDao = usersDao;
   }

   /**
    * This method loads the user by their userId, but in order to override the
    * function, we access it by "username"
    *
    * @param username  The user's id
    * @return
    * @throws UsernameNotFoundException
    */
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = usersDao.getUserObject(Long.parseLong(username));
      if (user == null) {
         throw new UsernameNotFoundException(username);
      }
      return new MyUserPrincipal(user);
   }
}

class MyUserPrincipal implements UserDetails {
   private User user;

   public MyUserPrincipal(User user) {
      this.user = user;
   }

   @Override
   public String getPassword() {
      return user.getPassword();
   }

   public String getUsername() {
      return user.getUsername();
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return user.getAuthorities();
   }
}
