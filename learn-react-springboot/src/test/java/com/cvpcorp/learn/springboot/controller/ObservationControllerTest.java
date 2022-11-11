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

import com.cvpcorp.learn.springboot.dto.ObservationDto;
import com.cvpcorp.learn.springboot.model.Observation;
import com.cvpcorp.learn.springboot.repository.ObservationRepo;

@ExtendWith(MockitoExtension.class)
class ObservationControllerTest {
	private Observation mockObservation = new Observation(); //Mock Observation to be used as input / output for tests
	private ObservationDto mockDto = new ObservationDto();

	@InjectMocks
    private ObservationController controller;

    @Mock
    private ObservationRepo repo;
	
    @Test
    void getAllObservations() throws Exception {
    	List<Observation> ObservationList = new ArrayList<Observation>();
    	
    	ObservationList.add(mockObservation);
    	
        doReturn(ObservationList).when(repo).findAll();

        ResponseEntity<List<ObservationDto>> response = controller.getAllObservations();
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void getOneObservation() throws Exception {
        doReturn(Optional.of(mockObservation)).when(repo).findById("MOCK_ID");

        ResponseEntity<ObservationDto> response = controller.getOneObservation("MOCK_ID");
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void getOneObservationFails() throws Exception {
        doReturn(Optional.ofNullable(null)).when(repo).findById("MOCK_ID");

        ResponseEntity<ObservationDto> response = controller.getOneObservation("MOCK_ID");
        assertTrue(response.getStatusCode().is4xxClientError());
    }
    
    @Test
    void addObservation() throws Exception {
        when(repo.save(any())).thenReturn(mockObservation);
        ResponseEntity<ObservationDto> response = controller.addObservation(mockDto);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void updateObservation() throws Exception {
        when(repo.save(any())).thenReturn(mockObservation);
        ResponseEntity<ObservationDto> response = controller.updateObservation(mockDto);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void deleteObservation() throws Exception {
        controller.deleteObservation("MOCK_ID");
        verify(repo, times(1)).deleteById("MOCK_ID");
    }
}