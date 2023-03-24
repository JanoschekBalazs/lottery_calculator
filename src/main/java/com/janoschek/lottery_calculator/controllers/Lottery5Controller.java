package com.janoschek.lottery_calculator.controllers;

import com.janoschek.lottery_calculator.repositories.Lottery5CombinationRepository;
import com.janoschek.lottery_calculator.services.combinations.Lottery5CombinationGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lottery5")
public class Lottery5Controller {

    @Autowired
    private Lottery5CombinationRepository repository;

    @Autowired
    private Lottery5CombinationGeneratorService generator;

    @PostMapping("generate")
    public void generate() {
        generator.generate();
    }
}
