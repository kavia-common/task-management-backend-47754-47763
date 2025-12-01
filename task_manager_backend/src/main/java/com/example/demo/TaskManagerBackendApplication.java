package com.example.taskmanagerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point for Task Manager Backend.
 */
@SpringBootApplication
public class TaskManagerBackendApplication {

	// PUBLIC_INTERFACE
	public static void main(String[] args) {
		/**
		 * Boots the Spring application.
		 */
		SpringApplication.run(TaskManagerBackendApplication.class, args);
	}

}
