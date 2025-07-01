package com.java.clinical.clinicalsapi.controllers;

import com.java.clinical.clinicalsapi.models.ClinicalData;
import com.java.clinical.clinicalsapi.models.Patients;
import com.java.clinical.clinicalsapi.repos.ClinicalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clinicaldata")
public class ClinicalDataController {

    @Autowired
    private ClinicalDataRepository clinicalDataRepository;
    @Autowired
    private com.java.clinical.clinicalsapi.repos.PatientsRepository patientsRepository;

    @GetMapping
    public List<ClinicalData> getAllClinicalData() {
        return clinicalDataRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalData> getClinicalDataById(@PathVariable Long id) {
        Optional<ClinicalData> clinicalData = clinicalDataRepository.findById(id);
        return clinicalData.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClinicalData createClinicalData(@RequestBody ClinicalData clinicalData) {
      if (clinicalData.getMeasuredDateTime() == null) {
        clinicalData.setMeasuredDateTime(java.time.LocalDateTime.now());
    }
    // Fetch and set the Patient entity
    if (clinicalData.getPatient() == null || clinicalData.getPatient().getId() == null) {
        throw new IllegalArgumentException("Patient ID must be provided");
    }
    Patients patient = patientsRepository.findById(clinicalData.getPatient().getId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));
    clinicalData.setPatient(patient);

    return clinicalDataRepository.save(clinicalData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalData> updateClinicalData(@PathVariable Long id, @RequestBody ClinicalData clinicalDataDetails) {
        Optional<ClinicalData> optionalClinicalData = clinicalDataRepository.findById(id);
        if (optionalClinicalData.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ClinicalData clinicalData = optionalClinicalData.get();
        clinicalData.setComponentName(clinicalDataDetails.getComponentName());
        clinicalData.setComponentValue(clinicalDataDetails.getComponentValue());
        clinicalData.setMeasuredDateTime(clinicalDataDetails.getMeasuredDateTime());
        clinicalData.setPatient(clinicalDataDetails.getPatient());
        return ResponseEntity.ok(clinicalDataRepository.save(clinicalData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicalData(@PathVariable Long id) {
        if (!clinicalDataRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clinicalDataRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}