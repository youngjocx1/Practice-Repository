package com.cvpcorp.learn.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cvpcorp.learn.springboot.dto.ConditionDto;
import com.cvpcorp.learn.springboot.maps.ConditionMap;
import com.cvpcorp.learn.springboot.model.Condition;
import com.cvpcorp.learn.springboot.repository.ConditionRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/conditions")
public class ConditionController {

    @Autowired
    private ConditionRepo repo;

    @GetMapping
    public ResponseEntity<List<ConditionDto>> getAllConditions() {
        List<ConditionDto> conditions = new ArrayList<>();
        repo.findAll().stream().forEach(c -> conditions.add(ConditionMap.map(c)));

        return ResponseEntity.ok(conditions);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ConditionDto> getOneCondition(@PathVariable("id") String id) {
        Condition condition = repo.findById(id).orElse(null);

        if(condition == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(ConditionMap.map(condition));
    }

    @PostMapping
    public ResponseEntity<ConditionDto> addCondition(@RequestBody ConditionDto dto) {
        Condition addedCondition = repo.save(ConditionMap.map(dto));

        return ResponseEntity.ok(ConditionMap.map(addedCondition));
    }

    @PutMapping
    public ResponseEntity<ConditionDto> updateCondition(@RequestBody ConditionDto dto) {
        Condition updatedCondition = repo.save(ConditionMap.map(dto));

        return ResponseEntity.ok(ConditionMap.map(updatedCondition));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCondition(@PathVariable("id") String id) {
        repo.deleteById(id);

        return ResponseEntity.ok("deleted");
    }
}
