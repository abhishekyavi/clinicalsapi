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

    @Test
    void testCreatePatient() {
        // Arrange
        PatientsRepository mockRepo = org.mockito.Mockito.mock(PatientsRepository.class);
        PatientsController controller = new PatientsController();
        java.lang.reflect.Field repoField;
        try {
            repoField = PatientsController.class.getDeclaredField("patientsRepository");
            repoField.setAccessible(true);
            repoField.set(controller, mockRepo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Patients inputPatient = new Patients();
        inputPatient.setFirstName("John");
        inputPatient.setLastName("Doe");
        inputPatient.setAge(30);

        Patients savedPatient = new Patients();
        savedPatient.setFirstName("John");
        savedPatient.setLastName("Doe");
        savedPatient.setAge(30);

        org.mockito.Mockito.when(mockRepo.save(inputPatient)).thenReturn(savedPatient);

        // Act
        Patients result = controller.createPatient(inputPatient);

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals("John", result.getFirstName());
        org.junit.jupiter.api.Assertions.assertEquals("Doe", result.getLastName());
        org.junit.jupiter.api.Assertions.assertEquals(30, result.getAge());
        org.mockito.Mockito.verify(mockRepo).save(inputPatient);
    }
}
