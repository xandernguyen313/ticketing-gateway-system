package com.project.ticketing_gateway.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ticketing_gateway.domain.Employee;
import com.project.ticketing_gateway.repository.EmployeeRepository;
import com.project.ticketing_gateway.service.EmployeeService;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;


    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository){
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.jsp
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // returns home.jsp
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup"; // signup.jsp
    }

     @PostMapping("/signup")
     public String processSignup(@RequestParam String password,
                                 @RequestParam String email,
                                 Model model) {
         // Basic duplicate check
         if (employeeRepository.findByEmail(email).isPresent()) {
             model.addAttribute("message", "Email already exists. Choose another one.");
             return "signup";
         }

         // Create user with encoded password & default role
         Employee created = employeeService.registerUser(email, password);

         // After successful sign-up, send them to login with a flag
         return "redirect:/login?registered=true";
     }
     
     @GetMapping("/welcome")
     public String welcomePage(Model model, Authentication authentication) {
         String email = authentication.getName();
         model.addAttribute("userEmail", email);
         return "welcome"; // login.jsp
     }
}
