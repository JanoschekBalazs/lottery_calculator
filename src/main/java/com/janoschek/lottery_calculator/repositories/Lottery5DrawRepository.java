package com.janoschek.lottery_calculator.repositories;

import com.janoschek.lottery_calculator.models.lottery5.Lottery5Draw;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Lottery5DrawRepository  extends MongoRepository<Lottery5Draw, String> {

    Lottery5Draw findByNumbers(List<Byte> numbers);

}
