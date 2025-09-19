package com.project.ticketing_gateway.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ticketing_gateway.domain.Employee;
import com.project.ticketing_gateway.domain.Role;
import com.project.ticketing_gateway.repository.EmployeeRepository;
import com.project.ticketing_gateway.repository.RoleRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	public EmployeeService(EmployeeRepository userRepository, PasswordEncoder passwordEncoder,
			RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	public Employee registerUser(String email, String rawPassword) {
		Employee user = new Employee();
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(rawPassword));
		Role userRole = roleRepository.findById(1L).orElse(null);
		user.getRoles().add(userRole);
		System.out.println(email);
		return userRepository.save(user);
	}
}
