package com.cvpcorp.learn.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cvpcorp.learn.springboot.dto.ObservationDto;
import com.cvpcorp.learn.springboot.model.Observation;
import com.cvpcorp.learn.springboot.repository.ObservationRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/observations")
public class ObservationController {

    @Autowired
    private ObservationRepo repo;

    @GetMapping
    public ResponseEntity<List<ObservationDto>> getAllObservations() {
        List<ObservationDto> observationDtos = new ArrayList<>();
        repo.findAll().stream().forEach(o -> observationDtos.add(map(o)));
        
        return ResponseEntity.ok(observationDtos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ObservationDto> getOneObservation(@PathVariable("id") String id) {
        Observation observation = repo.findById(id).orElse(null);

        return (observation != null ? ResponseEntity.ok(map(observation)) : ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ObservationDto> addObservation(@RequestBody ObservationDto observationDto) {
        Observation addedObservation = repo.save(map(observationDto));

        return ResponseEntity.ok(map(addedObservation));
    }

    @PutMapping
    public ResponseEntity<ObservationDto> updateObservation(@RequestBody ObservationDto observationDto) {
        Observation updatedObservation = repo.save(map(observationDto));

        return ResponseEntity.ok(map(updatedObservation));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteObservation(@PathVariable("id") String id) {
        repo.deleteById(id);

        return ResponseEntity.ok("deleted");
    }

    private ObservationDto map(Observation observation) {
        ObservationDto dto = new ObservationDto();
        dto.setId(observation.getId());
        dto.setDate(observation.getDate());
        dto.setCode(observation.getCode());
        dto.setDescription(observation.getDescription());
        dto.setUnits(observation.getUnits());
        dto.setUnitType(observation.getUnitType());
        dto.setValue(observation.getValue());
        dto.setEncounterId(observation.getEncounterId());

        return dto;
    }

    private Observation map(ObservationDto dto) {
        Observation observation = new Observation();
        observation.setId(dto.getId());
        observation.setDate(dto.getDate());
        observation.setCode(dto.getCode());
        observation.setDescription(dto.getDescription());
        observation.setUnits(dto.getUnits());
        observation.setUnitType(dto.getUnitType());
        observation.setValue(dto.getValue());
        observation.setEncounterId(dto.getEncounterId());

        return observation;
    }

}
