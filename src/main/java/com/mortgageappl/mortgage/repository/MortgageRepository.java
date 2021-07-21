package com.mortgageappl.mortgage.repository;

import com.mortgageappl.mortgage.model.Mortgage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MortgageRepository extends JpaRepository<Mortgage, Long> {
    boolean existsById(Long id);



}
