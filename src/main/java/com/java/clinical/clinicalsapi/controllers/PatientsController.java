package com.java.clinical.clinicalsapi.controllers;

import com.java.clinical.clinicalsapi.models.Patients;
import com.java.clinical.clinicalsapi.repos.PatientsRepository;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

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
    
 private final Counter patientsCreatedCounter;
    private final Timer getAllPatientsTimer;



   public PatientsController(MeterRegistry meterRegistry) {
        this.patientsCreatedCounter = Counter.builder("patients_created_total")
                .description("Total number of patients created")
                .register(meterRegistry);
        
        this.getAllPatientsTimer = Timer.builder("get_all_patients_duration")
                .description("Time taken to get all patients")
                .register(meterRegistry);
    }





    

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