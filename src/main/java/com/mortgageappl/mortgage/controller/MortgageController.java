package com.mortgageappl.mortgage.controller;

import com.mortgageappl.mortgage.exseption.MissmachCheckExeption;
import com.mortgageappl.mortgage.exseption.ResourceNotFoundException;
import com.mortgageappl.mortgage.model.Mortgage;
import com.mortgageappl.mortgage.repository.MortgageRepository;
import com.mortgageappl.mortgage.services.CheckAll;
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
    MortgageRepository mortgageRepository;
    @Autowired
    CheckAll checkAll;
    @GetMapping("/mortgage")
    public ResponseEntity<List<Mortgage>> getAllmortgage() throws ResourceNotFoundException {

        List<Mortgage> mortgageList = mortgageRepository.findAll();;
        if (mortgageList.isEmpty())
            throw new ResourceNotFoundException("Записи не найдены в базе данных!");
        return ResponseEntity.ok(mortgageList);
    }
    @GetMapping("/mortgage/{id}")
    public ResponseEntity<?> getMortgage(@PathVariable (value = "id") Long id) throws ResourceNotFoundException {
            if (! mortgageRepository.existsById(id)){
                throw new ResourceNotFoundException("Запись не найдена!");
            }
            return ResponseEntity.ok(mortgageRepository.getById(id));

    }

    @PostMapping("/mortgage")
    public ResponseEntity<Mortgage> saveMortgage (@RequestBody Mortgage mortgage) throws MissmachCheckExeption {
        checkAll.checkInn(mortgage.getInn());
        mortgageRepository.save(mortgage);
        return ResponseEntity.ok(mortgage);
    }

    @PutMapping(value = "/mortgage")
    public ResponseEntity<Mortgage> updateMortgage(@RequestBody Mortgage mortgage) throws ResourceNotFoundException, MissmachCheckExeption {
        checkAll.checkInn(mortgage.getInn());
        Optional<Mortgage> p = mortgageRepository.findById(mortgage.getId());
        if (!p.isPresent())
            throw new ResourceNotFoundException("Запись не найдена в базе данных!");
        //mortgageRepository.save(mortgage);
        return ResponseEntity.ok().body(mortgageRepository.save(mortgage));
    }

    @DeleteMapping(value = "/mortgage/{id}")
    public ResponseEntity<Mortgage> deleteMortgage(@PathVariable("id") Long id)  throws ResourceNotFoundException {
        Optional<Mortgage> p = mortgageRepository.findById(id);
        if (!p.isPresent())
            throw new ResourceNotFoundException("Запись не найдена в базе данных!");
        mortgageRepository.deleteById(id);
        return ResponseEntity.ok().body(p.get());
    }

}
