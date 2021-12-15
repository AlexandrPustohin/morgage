package com.mortgageappl.mortgage.controller;

import com.mortgageappl.mortgage.exseption.MismatchCheckException;
import com.mortgageappl.mortgage.exseption.ResourceNotFoundException;
import com.mortgageappl.mortgage.model.Mortgage;
import com.mortgageappl.mortgage.services.CheckAll;
import com.mortgageappl.mortgage.services.MortgageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

//origins = "http://192.168.1.167"
@CrossOrigin()
@RestController
public class MortgageController {

    @Autowired
    MortgageServiceImpl mortgageService;
    @Autowired
    CheckAll checkAll;

    @GetMapping("/mortgage")
    public ResponseEntity<List<Mortgage>> getAllMortgage() throws ResourceNotFoundException {


        return ResponseEntity.ok(mortgageService.getAllMortgage());
    }
    @GetMapping("/mortgage/{id}")
    public ResponseEntity<?> getMortgage(@PathVariable (value = "id") Long id) throws ResourceNotFoundException {
            if (! mortgageService.existsById(id)){
                throw new ResourceNotFoundException("Запись не найдена!");
            }
            return ResponseEntity.ok(mortgageService.getById(id));

    }

    @PostMapping("/mortgage")
    public ResponseEntity<Mortgage> saveMortgage (@RequestBody Mortgage mortgage) throws MismatchCheckException {
        checkAll.checkInn(mortgage.getInn());
        mortgageService.save(mortgage);
        return ResponseEntity.ok(mortgage);
    }

    @PutMapping(value = "/mortgage")
    public ResponseEntity<Mortgage> updateMortgage(@RequestBody Mortgage mortgage) throws ResourceNotFoundException, MismatchCheckException {
        checkAll.checkInn(mortgage.getInn());
        Optional<Mortgage> p = mortgageService.findById(mortgage.getId());
        if (!p.isPresent())
            throw new ResourceNotFoundException("Запись не найдена в базе данных!");
        return ResponseEntity.ok(mortgage);
    }

    @DeleteMapping(value = "/mortgage/{id}")
    public ResponseEntity<Mortgage> deleteMortgage(@PathVariable("id") Long id)  throws ResourceNotFoundException {
        Optional<Mortgage> p = mortgageService.findById(id);
        if (!p.isPresent())
            throw new ResourceNotFoundException("Запись не найдена в базе данных!");
        mortgageService.deleteById(id);
        return ResponseEntity.ok().body(p.get());
    }

}
