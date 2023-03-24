package com.janoschek.lottery_calculator.services.combinations;

import com.janoschek.lottery_calculator.models.lottery5.Lottery5Combination;
import com.janoschek.lottery_calculator.repositories.Lottery5CombinationRepository;
import org.paukov.combinatorics3.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class Lottery5CombinationGeneratorService {
    private static final Logger LOG = LoggerFactory.getLogger(Lottery5CombinationGeneratorService.class);
    private static final int BULK_SIZE = 200000;
    private static final Stream<Lottery5Combination> combinationStream = Generator
            .combination(IntStream.rangeClosed(1, Lottery5Combination.POOL_SIZE).boxed().collect(Collectors.toList()))
            .simple(Lottery5Combination.SIZE)
            .stream()
            .map(Lottery5Combination::new);

    @Autowired
    private Lottery5CombinationRepository repository;

    public void generate() {
        var it = combinationStream.iterator();
        var batch = new ArrayList<Lottery5Combination>(BULK_SIZE);
        var counter = 0;
        while(it.hasNext()) {
            counter++;
            batch.add(it.next());
            if (batch.size() % BULK_SIZE == 0) {
                repository.saveAll(batch);
                batch.clear();
                LOG.info("Saved " + counter + " item");
            }
        }
        repository.saveAll(batch);
        batch.clear();
    }
}
