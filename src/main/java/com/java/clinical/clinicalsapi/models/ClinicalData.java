package com.java.clinical.clinicalsapi.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clinical_data")
public class ClinicalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "component_name", nullable = false)
    private String componentName;

    @Column(name = "component_value", nullable = false)
    private String componentValue;

    @Column(name = "measured_date_time", nullable = true)
    private LocalDateTime measuredDateTime;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patients patient;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentValue() {
        return componentValue;
    }

    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }

    public LocalDateTime getMeasuredDateTime() {
        return measuredDateTime;
    }

    public void setMeasuredDateTime(LocalDateTime measuredDateTime) {
        this.measuredDateTime = measuredDateTime;
    }

    public Patients getPatient() {
        return patient;
    }

    public void setPatient(Patients patient) {
        this.patient = patient;
    }
}