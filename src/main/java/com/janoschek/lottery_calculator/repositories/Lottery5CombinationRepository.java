package com.janoschek.lottery_calculator.repositories;

import com.janoschek.lottery_calculator.models.lottery5.Lottery5Combination;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Lottery5CombinationRepository extends MongoRepository<Lottery5Combination, String> {

    Lottery5Combination findByNumbers(int[] numbers);

}
