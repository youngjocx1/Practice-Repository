package com.cvpcorp.learn.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cvpcorp.learn.springboot.dto.PatientDto;
import com.cvpcorp.learn.springboot.dto.PatientQueryDto;
import com.cvpcorp.learn.springboot.dto.PatientResponseDtoPaginated;
import com.cvpcorp.learn.springboot.maps.PatientMap;
import com.cvpcorp.learn.springboot.model.*;
import com.cvpcorp.learn.springboot.repository.PatientRepo;
import com.cvpcorp.learn.springboot.service.PatientService;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "/patients")
public class PatientController {
		
    @Autowired
    private PatientRepo repo;
    
    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patientDtos = new ArrayList<>();
        repo.findAll().stream().forEach(p -> patientDtos.add(PatientMap.map(p)));

    	return ResponseEntity.ok(patientDtos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PatientDto> getOnePatient(@PathVariable("id") String patientId) {
        Patient patient = repo.findByPatientId(patientId);

        return (patient != null ? ResponseEntity.ok(PatientMap.map(patient)) : ResponseEntity.notFound().build());
    }
    
    @GetMapping(path = "/{id}/medications")
    public ResponseEntity<List<Medication>> getMedicationsForPatient(@PathVariable("id") String patientId) {
        return ResponseEntity.ok(patientService.getMedicationsByPatientId(patientId));
    }
    
    @GetMapping(path = "/{id}/encounters")
    public ResponseEntity<List<Encounter>> getEncountersForPatient(@PathVariable("id") String patientId) {
        return ResponseEntity.ok(patientService.getEncountersByPatientId(patientId));
    }
    
    @GetMapping(path = "/{id}/conditions")
    public ResponseEntity<List<Condition>> getConditionsForPatient(@PathVariable("id") String patientId) {
        return ResponseEntity.ok(patientService.getConditionsByPatientId(patientId));
    }
    
    @GetMapping(path = "/{id}/observations")
    public ResponseEntity<List<Observation>> getObservationsForPatient(@PathVariable("id") String patientId) {
        return ResponseEntity.ok(patientService.getObservationsByPatientId(patientId));
    }
    
    @GetMapping(path = "/{id}/related/{ssn}")
    public ResponseEntity<List<Patient>> getObservationsForPatient(@PathVariable("id") String patientId, @PathVariable("ssn") String ssn) {
        return ResponseEntity.ok(patientService.getRelatedPatientsBySSN(patientId, ssn));
    }
    
    @PostMapping(path = "/paginated")
    public ResponseEntity<PatientResponseDtoPaginated> getPaginatedPatients(
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "25") Integer maxPerPage,
            @RequestBody(required = false) PatientQueryDto patientQuery) {

        PatientResponseDtoPaginated patientList = this.patientService.getPaginatedPatients(pageNumber, maxPerPage, patientQuery);
    	
    	return ResponseEntity.ok(patientList);
    }

    @PostMapping
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patientDto) {
        Patient addedPatient = repo.save(PatientMap.map(patientDto));

        return ResponseEntity.ok(PatientMap.map(addedPatient));
    }

    @PutMapping
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto) {
        Patient updatedPatient = repo.save(PatientMap.map(patientDto));

        return ResponseEntity.ok(PatientMap.map(updatedPatient));
    }

    @DeleteMapping
    public ResponseEntity<String> deletePatient(@PathVariable("id") String id) {
        repo.deleteById(id);

        return ResponseEntity.ok("deleted");
    }
}
