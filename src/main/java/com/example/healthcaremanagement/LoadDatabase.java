package com.example.healthcaremanagement;

import com.example.healthcaremanagement.Models.ERole;
import com.example.healthcaremanagement.Models.Medicine;
import com.example.healthcaremanagement.Models.Role;
import com.example.healthcaremanagement.Repositories.MedicineRepository;
import com.example.healthcaremanagement.Models.Report;
import com.example.healthcaremanagement.Repositories.ReportRepository;
import com.example.healthcaremanagement.Models.User;
import com.example.healthcaremanagement.Repositories.RoleRepository;
import com.example.healthcaremanagement.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.example.healthcaremanagement.Models.ERole.ROLE_ADMIN;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ReportRepository reportRepository, MedicineRepository medicineRepository, RoleRepository roleRepository) {

        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

        List<Medicine> meds = new ArrayList<>();
        meds.add(new Medicine(1, 10, "Acetominophen", "Generic", "Headaches, Fever, Pain", "2023", 100, new ArrayList<>()));
        meds.add(new Medicine(2, 10, "Ibuprofen", "Generic", "Headaches, Fever, Pain", "2023", 100, new ArrayList<>()));
        meds.add(new Medicine(3, 10, "Penicillin", "Generic", "Antibiotic", "2023", 100, new ArrayList<>()));

        List<Role> roles = new ArrayList<>();
        roles.add(new Role(ROLE_ADMIN));
        roles.add(new Role(ERole.ROLE_USER));

        List<User> users = new ArrayList<>();
//        users.add(new User(1, 123456789, 1000, false, "12/12/1912", "Joe", "Doe",  "johndoe@example.com", "johndoe@example.com", bcryptPasswordEncoder.encode("Ex@mple1$%"), meds, new ArrayList<>()));
//        users.add(new User(1, 123456789, 1000, true, "12/12/1912", "Jane", "Doe",  "janedoe@example.com", "johndoe@example.com", bcryptPasswordEncoder.encode("Ex@mple2$%"), meds, new ArrayList<>()));
//        users.add(new User(1, 123456789, 1000, false, "12/12/1912", "Jack", "Doe",  "jackdoe@example.com", "jackdoe@example.com", bcryptPasswordEncoder.encode("Ex@mple3$%"), meds, new ArrayList<>()));
//        users.add(new User("Admin", "test_admin@test.com", bcryptPasswordEncoder.encode("admin"), "Jane", "Doe", 123456789L, new HashSet<>()));
//        users.add(new User("User", "test_user@test.com", bcryptPasswordEncoder.encode("user"), "John", "Doe", 123456789L,  new HashSet<>()));

        return args -> {

            // Load med table
            log.info("Preloading " + medicineRepository.save(meds.get(0)));
            log.info("Preloading " + medicineRepository.save(meds.get(1)));
            log.info("Preloading " + medicineRepository.save(meds.get(2)));

            // Load report table
            log.info("Force create empty Report table");
            reportRepository.save(new Report());

            roleRepository.save(roles.get(0));
            roleRepository.save(roles.get(1));

            // Load user table
//            log.info("Preloading " + userRepository.save(users.get(0)));
//            log.info("Preloading " + userRepository.save(users.get(1)));
//            log.info("Preloading " + userRepository.save(users.get(2)));

        };
    }
}
