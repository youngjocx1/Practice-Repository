package com.cvpcorp.learn.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cvpcorp.learn.springboot.dto.EncounterDto;
import com.cvpcorp.learn.springboot.maps.EncounterMap;
import com.cvpcorp.learn.springboot.model.Encounter;
import com.cvpcorp.learn.springboot.repository.EncounterRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/encounters")
public class EncounterController {

    @Autowired
    private EncounterRepo repo;

    @GetMapping
    public ResponseEntity<List<EncounterDto>> getAllEncounters() {
        List<EncounterDto> encounterDtos = new ArrayList<>();
        repo.findAll().stream().forEach(e -> encounterDtos.add(EncounterMap.map(e)));

        return ResponseEntity.ok(encounterDtos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EncounterDto> getOneEncounter(@PathVariable("id") String id) {
        Encounter encounter = repo.findById(id).orElse(null);

        return (encounter != null ? ResponseEntity.ok(EncounterMap.map(encounter)) : ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EncounterDto> addEncounter(@RequestBody EncounterDto encounterDto) {
        Encounter addedEncounter = repo.save(EncounterMap.map(encounterDto));

        return ResponseEntity.ok(EncounterMap.map(addedEncounter));
    }

    @PutMapping
    public ResponseEntity<EncounterDto> updateEncounter(@RequestBody EncounterDto encounterDto) {
        Encounter updatedEncounter = repo.save(EncounterMap.map(encounterDto));

        return ResponseEntity.ok(EncounterMap.map(updatedEncounter));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEncounter(@PathVariable("id") String id) {
        repo.deleteById(id);

        return ResponseEntity.ok("deleted");
    }
}
