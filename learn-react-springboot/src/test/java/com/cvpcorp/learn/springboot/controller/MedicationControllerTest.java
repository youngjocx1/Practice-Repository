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

import com.cvpcorp.learn.springboot.dto.MedicationDto;
import com.cvpcorp.learn.springboot.model.Medication;
import com.cvpcorp.learn.springboot.repository.MedicationRepo;

@ExtendWith(MockitoExtension.class)
class MedicationControllerTest {
	private Medication mockMedication = new Medication(); //Mock Medication to be used as input / output for tests
	private MedicationDto mockDto = new MedicationDto();

	@InjectMocks
    private MedicationController controller;

    @Mock
    private MedicationRepo repo;
	
    @Test
    void getAllMedications() throws Exception {
    	List<Medication> MedicationList = new ArrayList<Medication>();
    	
    	MedicationList.add(mockMedication);
    	
        doReturn(MedicationList).when(repo).findAll();

        ResponseEntity<List<MedicationDto>> response = controller.getAllMedications();
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void getOneMedication() throws Exception {
        doReturn(Optional.of(mockMedication)).when(repo).findById("MOCK_ID");

        ResponseEntity<MedicationDto> response = controller.getOneMedication("MOCK_ID");
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void getOneMedicationFails() throws Exception {
        doReturn(Optional.ofNullable(null)).when(repo).findById("MOCK_ID");

        ResponseEntity<MedicationDto> response = controller.getOneMedication("MOCK_ID");
        assertTrue(response.getStatusCode().is4xxClientError());
    }
    
    @Test
    void addMedication() throws Exception {
        when(repo.save(any())).thenReturn(mockMedication);
        ResponseEntity<MedicationDto> response = controller.addMedication(mockDto);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void updateMedication() throws Exception {
        when(repo.save(any())).thenReturn(mockMedication);
        ResponseEntity<MedicationDto> response = controller.updateMedication(mockDto);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void deleteMedication() throws Exception {
        controller.deleteMedication("MOCK_ID");
        verify(repo, times(1)).deleteById("MOCK_ID");
    }
}