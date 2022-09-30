package com.example.healthcaremanagement.Controllers;

import com.example.healthcaremanagement.Exceptions.MedicineNotFoundException;
import com.example.healthcaremanagement.Repositories.MedicineRepository;
import com.example.healthcaremanagement.Models.Medicine;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MedicineController {

    private final MedicineRepository repository;

    @PostMapping("/medicines")
    @PreAuthorize("hasRole('ADMIN')")
    Medicine newMedicine(@RequestBody Medicine newMedicine) {
        return repository.save(newMedicine);
    }

    @PutMapping("/medicines/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Medicine updateMedicine(@RequestBody Medicine newMedicine, @PathVariable Long id) {

        return repository.findById(id)
                .map(medicine -> {

                    medicine.setQuantity(newMedicine.getQuantity());
                    medicine.setMedName(newMedicine.getMedName());
                    medicine.setCompanyName(newMedicine.getCompanyName());
                    medicine.setUses(newMedicine.getUses());
                    medicine.setExpDate(newMedicine.getExpDate());
                    return repository.save(medicine);
                }).orElseGet(() -> {
                    return repository.save(newMedicine);
                });
    }

    @GetMapping("/medicines")
    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    ResponseEntity<List<Medicine>> all() {

        return ResponseEntity.ok(repository.findAll());
    }


    @GetMapping("/medicines/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Medicine one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new MedicineNotFoundException(id));
    }

    @DeleteMapping("/medicines/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteMedicine(@PathVariable Long id) {

        Optional<Medicine> medicine = repository.findById(id);
        if(medicine.isEmpty())
            throw new MedicineNotFoundException(id);

        repository.deleteById(id);
    }
}
