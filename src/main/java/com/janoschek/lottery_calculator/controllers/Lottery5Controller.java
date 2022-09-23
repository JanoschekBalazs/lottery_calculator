package com.janoschek.lottery_calculator.controllers;

import com.janoschek.lottery_calculator.models.lottery5.Lottery5Ticket;
import com.janoschek.lottery_calculator.repositories.Lottery5CombinationRepository;
import com.janoschek.lottery_calculator.services.combinations.Lottery5CombinationGeneratorService;
import com.janoschek.lottery_calculator.services.history.Lottery5DrawImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lottery5")
public class Lottery5Controller {

    @Autowired
    private Lottery5CombinationRepository repository;

    @Autowired
    private Lottery5CombinationGeneratorService generator;

    @Autowired
    private Lottery5DrawImportService drawImport;

    @GetMapping
    public List<Lottery5Ticket> list() {
        return repository.findAll();
    }

    @GetMapping
    @RequestMapping("getByNumbers")
    public Lottery5Ticket getByNumbers(@RequestBody final Lottery5Ticket ticket) {
        return repository.findByNumbers(ticket.getNumbers());
    }

    @PostMapping
    public Lottery5Ticket create(@RequestBody final Lottery5Ticket ticket) {
        return repository.save(ticket);
    }

    @PostMapping("generate")
    public void generate() {
        generator.generate();
    }

    @PostMapping("import")
    public void importAll() {
        drawImport.importAll();
    }

}
