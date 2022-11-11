package com.cvpcorp.learn.springboot.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.cvpcorp.learn.springboot.dto.PatientDto;
import com.cvpcorp.learn.springboot.model.Patient;
import com.cvpcorp.learn.springboot.repository.PatientRepo;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {	
    private Patient mockPatient = new Patient(); //Mock Patient to be used as input / output for tests
    private PatientDto mockDto = new PatientDto();

    @InjectMocks
    private PatientController controller;
    
    @Mock 
    private PatientRepo repo;
   
    @Test
    void getAllPatients() throws Exception {
    	List<Patient> PatientList = new ArrayList<Patient>();
    	
    	PatientList.add(mockPatient);
    	
        doReturn(PatientList).when(repo).findAll();
        
        ResponseEntity<List<PatientDto>> response = controller.getAllPatients();

        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    
    @Test
    void getOnePatient() throws Exception {
        when(repo.findByPatientId("MOCK_ID")).thenReturn(mockPatient);

        ResponseEntity<PatientDto> response = controller.getOnePatient("MOCK_ID");
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void getOnePatientFails() throws Exception {
        when(repo.findByPatientId("MOCK_ID")).thenReturn(null);

        ResponseEntity<PatientDto> response = controller.getOnePatient("MOCK_ID");
        assertTrue(response.getStatusCode().is4xxClientError());
    }
    
    @Test
    void addPatient() throws Exception {
        when(repo.save(any())).thenReturn(mockPatient);
        ResponseEntity<PatientDto> response = controller.addPatient(mockDto);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void updatePatient() throws Exception {
        when(repo.save(any())).thenReturn(mockPatient);
        ResponseEntity<PatientDto> response = controller.updatePatient(mockDto);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void deletePatient() throws Exception {
        controller.deletePatient("MOCK_ID");
        verify(repo, times(1)).deleteById("MOCK_ID");
    }
   
}