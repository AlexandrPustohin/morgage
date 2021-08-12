package com.mortgageappl.mortgage.services;

import com.mortgageappl.mortgage.exseption.ResourceNotFoundException;
import com.mortgageappl.mortgage.model.Mortgage;
import com.mortgageappl.mortgage.repository.MortgageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MortageServiceImpl {

    @Autowired
    MortgageRepository mortgageRepository;

    public List<Mortgage> getAllMotrgage() throws ResourceNotFoundException {
        List<Mortgage> mortgageList = mortgageRepository.findAll();;
        if (mortgageList.isEmpty())
            throw new ResourceNotFoundException("Записи не найдены в базе данных!");
        return mortgageRepository.findAll();
    }
}
