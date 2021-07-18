package com.mortgageappl.mortgage.controller;

import com.mortgageappl.mortgage.dto.DTOMorgage;
import com.mortgageappl.mortgage.exseption.ErrorDetails;
import com.mortgageappl.mortgage.exseption.GlobalExceptionHandler;
import com.mortgageappl.mortgage.exseption.MissmachCheckExeption;
import com.mortgageappl.mortgage.exseption.ResourceNotFoundException;
import com.mortgageappl.mortgage.model.Morgage;
import com.mortgageappl.mortgage.repository.MorgageRepository;
import com.mortgageappl.mortgage.services.CheckAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class MorgageController {

    @Autowired
    MorgageRepository morgageRepository;
    @Autowired
    CheckAll checkAll;
    @GetMapping("/morgage")
    public ResponseEntity<List<Morgage>> getAllmorgage(){

        return ResponseEntity.ok(morgageRepository.findAll());
    }
    @GetMapping("/morgage/{id}")
    public ResponseEntity<?> getMorgage(@PathVariable (value = "id") Long id) throws ResourceNotFoundException {
            if (! morgageRepository.existsById(id)){
                throw new ResourceNotFoundException("Запись не найдена!");
            }
            return ResponseEntity.ok(morgageRepository.getById(id));

    }

    @PostMapping("/morgage")
    public ResponseEntity<Morgage> saveMorgage (@RequestBody Morgage morgage) throws MissmachCheckExeption {
        checkAll.checkInn(morgage.getInn());
        morgageRepository.save(morgage);
        return ResponseEntity.ok(morgage);
    }

    @PutMapping(value = "/morgage/{id}")
    public ResponseEntity<Morgage> updateMorgage(@RequestBody Morgage morgage,
                                               @PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Morgage> p = morgageRepository.findById(id);
        if (!p.isPresent())
            throw new ResourceNotFoundException("Запись не найдена в базе данных!");
        morgage.setId(id);
        return ResponseEntity.ok().body(morgageRepository.save(morgage));
    }

    @DeleteMapping(value = "/morgage/{id}")
    public ResponseEntity<Morgage> deleteMorgage(@PathVariable("id") Long id)  throws ResourceNotFoundException {
        Optional<Morgage> p = morgageRepository.findById(id);
        if (!p.isPresent())
            throw new ResourceNotFoundException("Запись не найдена в базе данных!");
        morgageRepository.deleteById(id);
        return ResponseEntity.ok().body(p.get());
    }

}
