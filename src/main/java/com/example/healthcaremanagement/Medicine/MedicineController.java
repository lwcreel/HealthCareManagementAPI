package com.example.healthcaremanagement.Medicine;

import com.example.healthcaremanagement.Medicine.Medicine;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class MedicineController {

    private final MedicineRepository repository;

    @PostMapping("/medicines")
    Medicine newMedicine(@RequestBody Medicine newMedicine) {
        return repository.save(newMedicine);
    }

    @PutMapping("/medicines/{id}")
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
    List<Medicine> all() {
        return repository.findAll();
    }

    @GetMapping("/medicines/{id}")
    Medicine one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new MedicineNotFoundException(id));
    }

    @DeleteMapping("/medicines/{id}")
    void deleteMedicine(@PathVariable Long id) {

        Optional<Medicine> medicine = repository.findById(id);
        if(medicine.isEmpty())
            throw new MedicineNotFoundException(id);

        repository.deleteById(id);
    }
}
