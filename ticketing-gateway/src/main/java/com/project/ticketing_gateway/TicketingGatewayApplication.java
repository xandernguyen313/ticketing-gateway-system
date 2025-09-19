package com.project.ticketing_gateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.ticketing_gateway.domain.Role;
import com.project.ticketing_gateway.repository.RoleRepository;

@SpringBootApplication
public class TicketingGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketingGatewayApplication.class, args);
	}
	@Bean
        CommandLineRunner seedData(RoleRepository roleRepo) {
                return args -> {
                        // Ensure USER role
                        Role userRole = roleRepo.findByName("USER").orElseGet(() -> {
                                Role r = new Role();
                                r.setName("USER");
                                return roleRepo.save(r);
                        });
                
                        // Ensure ADMIN role
                        Role adminRole = roleRepo.findByName("ADMIN").orElseGet(() -> {
                                Role r = new Role();
                                r.setName("ADMIN");
                                return roleRepo.save(r);
                        });
	        };
        }
}