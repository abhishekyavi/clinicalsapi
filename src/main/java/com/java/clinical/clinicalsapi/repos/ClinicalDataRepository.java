package com.java.clinical.clinicalsapi.repos;
import com.java.clinical.clinicalsapi.models.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {
    // Add custom query methods if needed
}