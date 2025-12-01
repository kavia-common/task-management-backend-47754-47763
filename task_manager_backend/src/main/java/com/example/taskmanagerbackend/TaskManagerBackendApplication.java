package com.example.taskmanagerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Application entry point for Task Manager Backend.
 * Ensures any JDBC/JPA auto-configurations are excluded so the app runs purely in-memory.
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
public class TaskManagerBackendApplication {

    // PUBLIC_INTERFACE
    public static void main(String[] args) {
        /**
         * Boots the Spring application.
         */
        SpringApplication.run(TaskManagerBackendApplication.class, args);
    }
}
