package com.java.clinical.clinicalsapi.repos;

import com.java.clinical.clinicalsapi.models.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long> {
    // You can add custom query methods here if needed
}