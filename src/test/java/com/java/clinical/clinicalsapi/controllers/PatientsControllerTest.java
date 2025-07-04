package com.java.clinical.clinicalsapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.java.clinical.clinicalsapi.models.Patients;
import com.java.clinical.clinicalsapi.repos.PatientsRepository;





public class PatientsControllerTest {

    @Autowired  PatientsRepository patientsRepository ;
    @Test
    void testGetAllPatients() {
        System.out.println("testGetAllPatients executed");

    }

  
}
