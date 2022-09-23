package com.janoschek.lottery_calculator.services.combinations;

import com.janoschek.lottery_calculator.models.lottery5.Lottery5Ticket;
import com.janoschek.lottery_calculator.repositories.Lottery5CombinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Lottery5CombinationGeneratorService implements LotteryCombinationsGeneratorService {

    private static final Lottery5Rules rules = Lottery5Rules.get();
    private static final int BULK_SIZE = 200000;
    private static final List<Lottery5Ticket> tickets = new ArrayList<>(BULK_SIZE);
    @Autowired
    private Lottery5CombinationRepository repository;

    @Override
    public void generate() {
        rules.getCombinationStream().forEach(combination -> tickets.add(new Lottery5Ticket(combination)));
        List<Lottery5Ticket> bulk = new ArrayList<>(BULK_SIZE);
        for (int i = 0; i < tickets.size(); i++) {
            bulk.add(tickets.get(i));
            if (i != 0 && i % BULK_SIZE == 0) {
                synchronizedSave(bulk);
                bulk.clear();
            }
        }
        synchronizedSave(bulk);
    }

    private void synchronizedSave(List<Lottery5Ticket> items) {
        synchronized (repository) {
            repository.saveAll(items);
        }
    }
}
