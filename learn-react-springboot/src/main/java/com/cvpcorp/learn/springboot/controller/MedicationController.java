package com.cvpcorp.learn.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cvpcorp.learn.springboot.dto.MedicationDto;
import com.cvpcorp.learn.springboot.maps.MedicationMap;
import com.cvpcorp.learn.springboot.model.Medication;
import com.cvpcorp.learn.springboot.repository.MedicationRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/medications")
public class MedicationController {

    @Autowired
    private MedicationRepo repo;

    @GetMapping
    public ResponseEntity<List<MedicationDto>> getAllMedications() {
        List<MedicationDto> medicationDtos = new ArrayList<>();
        repo.findAll().stream().forEach(m -> medicationDtos.add(MedicationMap.map(m)));

        return ResponseEntity.ok(medicationDtos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MedicationDto> getOneMedication(@PathVariable("id") String id) {
        Medication medication = repo.findById(id).orElse(null);

        return (medication != null ? ResponseEntity.ok(MedicationMap.map(medication)) : ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicationDto> addMedication(@RequestBody MedicationDto medicationDto) {
        Medication addedMedication = repo.save(MedicationMap.map(medicationDto));

        return ResponseEntity.ok(MedicationMap.map(addedMedication));
    }

    @PutMapping
    public ResponseEntity<MedicationDto> updateMedication(@RequestBody MedicationDto medicationDto) {
        Medication updatedMedication = repo.save(MedicationMap.map(medicationDto));

        return ResponseEntity.ok(MedicationMap.map(updatedMedication));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMedication(@PathVariable("id") String id) {
        repo.deleteById(id);

        return ResponseEntity.ok("deleted");
    }

}
