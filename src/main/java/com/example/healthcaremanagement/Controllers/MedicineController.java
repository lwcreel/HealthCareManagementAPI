package com.example.healthcaremanagement.Controllers;

import com.example.healthcaremanagement.Exceptions.MedicineNotFoundException;
import com.example.healthcaremanagement.Repositories.MedicineRepository;
import com.example.healthcaremanagement.Models.Medicine;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class MedicineController {

    private final MedicineRepository repository;

    @PostMapping("/medicines")
    @PreAuthorize("hasAuthority('ADMIN')")
    Medicine newMedicine(@RequestBody Medicine newMedicine) {
        return repository.save(newMedicine);
    }

    @PutMapping("/medicines/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    List<Medicine> all() {
        return repository.findAll();
    }

    @GetMapping("/medicines/user")
    //@PreAuthorize("hasAuthority('USER')")
    List<Map> listMeds() {

        List<Map> retval = new ArrayList<>();
        List<Medicine> meds = repository.findAll();

        for (Medicine m: meds) {
            Map<String, String> map = new HashMap<>() {
                {
                    put("id", Long.toString(m.getId()));
                    put("medicineName", m.getMedName());
                    put("price", Integer.toString(m.getCostFactor()));
                    put("quantity", Integer.toString(0));
                }
            };
        }

        return retval;
    }

    @GetMapping("/medicines/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Medicine one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new MedicineNotFoundException(id));
    }

    @DeleteMapping("/medicines/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    void deleteMedicine(@PathVariable Long id) {

        Optional<Medicine> medicine = repository.findById(id);
        if(medicine.isEmpty())
            throw new MedicineNotFoundException(id);

        repository.deleteById(id);
    }
}
