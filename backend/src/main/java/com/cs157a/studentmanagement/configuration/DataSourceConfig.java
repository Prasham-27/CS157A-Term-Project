package com.cs157a.studentmanagement.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

// TODO find another way so this doesn't interfere with automatic creations
@Configuration
public class DataSourceConfig {

   @Value("${spring.datasource.url}")
   private String dbUrl;

   @Value("${spring.datasource.driver-class-name}")
   private String dbDriverClassName;

   // TODO change default to login later
   @Value("${spring.datasource.username}")
   private String defaultDbUsername;

   @Value("${spring.datasource.password}")
   private String defaultDbPassword;


   private EntityManagerFactory entityManagerFactory;

   @Bean
   @DependsOn("Roles")
   public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
      ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
      populator.setContinueOnError(true); // Continue even if a query fails
      populator.setIgnoreFailedDrops(true); // Log while ignoring
      populator.addScript(new ClassPathResource("data.sql"));

      DataSourceInitializer initializer = new DataSourceInitializer();
      initializer.setDataSource(dataSource);
      initializer.setDatabasePopulator(populator);
      initializer.setEnabled(true); // Ensures execution


      return initializer;
   }

   /**
    * @return The default datasource, which is the SQL login account
    */
   @Bean
   public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(dbDriverClassName);
      dataSource.setUrl(dbUrl);
      dataSource.setUsername(defaultDbUsername);
      dataSource.setPassword(defaultDbPassword);
      return dataSource;
   }

   public DataSource getDataSource(String username, String password) {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(dbDriverClassName);
      dataSource.setUrl(dbUrl);
      dataSource.setUsername(username);  // Dynamic user from the session
      dataSource.setPassword(password);  // Password for the session user
      return dataSource;
   }
}
