package com.cvpcorp.learn.springboot.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.cvpcorp.learn.springboot.dto.ConditionDto;
import com.cvpcorp.learn.springboot.model.Condition;
import com.cvpcorp.learn.springboot.repository.ConditionRepo;

@ExtendWith(MockitoExtension.class)
class ConditionControllerTest {
	private Condition mockCondition = new Condition(); //Mock condition to be used as input / output for tests
	private ConditionDto mockDto = new ConditionDto();

	@InjectMocks
    private ConditionController controller;

    @Mock
    private ConditionRepo repo;

    @Test
    void getAllConditions() throws Exception {
    	List<Condition> conditionList = new ArrayList<Condition>();
    	
    	conditionList.add(mockCondition);
    	
        doReturn(conditionList).when(repo).findAll();

        ResponseEntity<List<ConditionDto>> response = controller.getAllConditions();
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void getOneCondition() throws Exception {
        doReturn(Optional.of(mockCondition)).when(repo).findById("MOCK_ID");

        ResponseEntity<ConditionDto> response = controller.getOneCondition("MOCK_ID");
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void getOneConditionFails() throws Exception {
        doReturn(Optional.ofNullable(null)).when(repo).findById("MOCK_ID");

        ResponseEntity<ConditionDto> response = controller.getOneCondition("MOCK_ID");
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void addCondition() throws Exception {
        when(repo.save(any()))  .thenReturn(mockCondition);
        ResponseEntity<ConditionDto> response = controller.addCondition(mockDto);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void updateCondition() throws Exception {
        when(repo.save(any()))  .thenReturn(mockCondition);
        ResponseEntity<ConditionDto> response = controller.updateCondition(mockDto);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    void deleteCondition() throws Exception {
        controller.deleteCondition("MOCK_ID");
        verify(repo, times(1)).deleteById("MOCK_ID");
    }
}