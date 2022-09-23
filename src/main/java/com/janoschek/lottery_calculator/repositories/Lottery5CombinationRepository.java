package com.janoschek.lottery_calculator.repositories;

import com.janoschek.lottery_calculator.models.lottery5.Lottery5Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Lottery5CombinationRepository extends MongoRepository<Lottery5Ticket, String> {

    Lottery5Ticket findByNumbers(List<Integer> numbers);

}
