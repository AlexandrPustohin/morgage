package com.mortgageappl.mortgage.repository;

import com.mortgageappl.mortgage.model.Morgage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MorgageRepository extends JpaRepository<Morgage, Long> {
    boolean existsById(Long id);



}
