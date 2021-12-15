package com.mortgageappl.mortgage.services;

import com.mortgageappl.mortgage.exseption.ResourceNotFoundException;
import com.mortgageappl.mortgage.model.Mortgage;
import com.mortgageappl.mortgage.repository.MortgageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MortgageServiceImpl {

    @Autowired
    MortgageRepository mortgageRepository;

    public List<Mortgage> getAllMortgage() throws ResourceNotFoundException {
        List<Mortgage> mortgageList = mortgageRepository.findAll();;
        if (mortgageList.isEmpty())
            throw new ResourceNotFoundException("Записи не найдены в базе данных!");
        return mortgageRepository.findAll();
    }

    public boolean existsById(Long id) {
        return mortgageRepository.existsById(id);
    }

    public Object getById(Long id) {
        return mortgageRepository.getById(id);
    }

    public void save(Mortgage mortgage) {
        mortgageRepository.save(mortgage);
    }

    public Optional<Mortgage> findById(Long id) {
        return mortgageRepository.findById(id);
    }

    public void deleteById(Long id) {
        mortgageRepository.deleteById(id);
    }
}
