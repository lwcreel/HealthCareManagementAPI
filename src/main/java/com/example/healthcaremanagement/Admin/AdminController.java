package com.example.healthcaremanagement.Admin;

import com.example.healthcaremanagement.User.User;
import com.example.healthcaremanagement.User.UserNotFoundException;
import com.example.healthcaremanagement.User.UserRepository;
import com.example.healthcaremanagement.Medicine.MedicineRepository;
import com.example.healthcaremanagement.Report.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/admin/login")
    @PreAuthorize("hasAuthority('ADMIN')")
    String loginAdmin (@RequestParam("id") Long id,
                      @RequestParam("password") String password) throws UserNotFoundException{

        List<User> users = userRepository.findAll();
        for (User a : users) {
            if (a.getId() == id && a.getPassword().equals(password) && a.isAdmin())
                return "Login Successful, welcome " + a.getFirstName() + " " + a.getLastName();
        }
        throw new UserNotFoundException(id);
    }
}
