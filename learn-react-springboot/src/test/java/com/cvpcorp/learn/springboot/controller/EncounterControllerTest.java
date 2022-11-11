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

import com.cvpcorp.learn.springboot.dto.EncounterDto;
import com.cvpcorp.learn.springboot.model.Encounter;
import com.cvpcorp.learn.springboot.repository.EncounterRepo;

@ExtendWith(MockitoExtension.class)
class EncounterControllerTest {
	private Encounter mockEncounter = new Encounter(); //Mock encounter to be used as input / output for tests
	private EncounterDto mockDto = new EncounterDto();

	@InjectMocks
    private EncounterController controller;

    @Mock
    private EncounterRepo repo;
	
    @Test
    void getAllEncounters() throws Exception {
    	List<Encounter> encounterList = new ArrayList<Encounter>();
    	
    	encounterList.add(mockEncounter);
    	
        doReturn(encounterList).when(repo).findAll();

        ResponseEntity<List<EncounterDto>> response = controller.getAllEncounters();
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void getOneEncounter() throws Exception {
        doReturn(Optional.of(mockEncounter)).when(repo).findById("MOCK_ID");

        ResponseEntity<EncounterDto> response = controller.getOneEncounter("MOCK_ID");
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void getOneEncounterFails() throws Exception {
        doReturn(Optional.ofNullable(null)).when(repo).findById("MOCK_ID");

        ResponseEntity<EncounterDto> response = controller.getOneEncounter("MOCK_ID");
        assertTrue(response.getStatusCode().is4xxClientError());
    }
    
    @Test
    void addEncounter() throws Exception {
        when(repo.save(any())).thenReturn(mockEncounter);
        ResponseEntity<EncounterDto> response = controller.addEncounter(mockDto);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void updateEncounter() throws Exception {
        when(repo.save(any())).thenReturn(mockEncounter);
        ResponseEntity<EncounterDto> response = controller.updateEncounter(mockDto);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void deleteEncounter() throws Exception {
        controller.deleteEncounter("MOCK_ID");
        verify(repo, times(1)).deleteById("MOCK_ID");
    }
}