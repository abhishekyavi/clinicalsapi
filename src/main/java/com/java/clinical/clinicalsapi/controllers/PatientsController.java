package com.java.clinical.clinicalsapi.controllers;

import com.java.clinical.clinicalsapi.models.Patients;
import com.java.clinical.clinicalsapi.repos.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    @Autowired
    private PatientsRepository patientsRepository;

    @GetMapping
    public List<Patients> getAllPatients() {
        return patientsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patients> getPatientById(@PathVariable Long id) {
        Optional<Patients> patient = patientsRepository.findById(id);
        return patient.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patients createPatient(@RequestBody Patients patient) {
        return patientsRepository.save(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patients> updatePatient(@PathVariable Long id, @RequestBody Patients patientDetails) {
        Optional<Patients> optionalPatient = patientsRepository.findById(id);
        if (optionalPatient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Patients patient = optionalPatient.get();
        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setAge(patientDetails.getAge());
        return ResponseEntity.ok(patientsRepository.save(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (!patientsRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        patientsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}