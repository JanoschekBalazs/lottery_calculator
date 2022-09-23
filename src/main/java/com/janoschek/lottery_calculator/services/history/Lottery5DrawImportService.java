package com.janoschek.lottery_calculator.services.history;

import com.janoschek.lottery_calculator.models.lottery5.Lottery5Draw;
import com.janoschek.lottery_calculator.repositories.Lottery5DrawRepository;
import org.apache.commons.csv.CSVFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Lottery5DrawImportService implements LotteryDrawImportService {

    @Autowired
    Lottery5DrawRepository repository;

    @Override
    public void importAll() {
        try (Reader in = new FileReader(new ClassPathResource("otos.csv").getFile())) {
            List<Lottery5Draw> draws = CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader().setDelimiter(';').build()
                .parse(in).stream().map(Lottery5Draw::fromCSVRecord).collect(Collectors.toList());
            repository.saveAll(draws);
        } catch (IOException e) {
            throw new ImportUnsuccessfulException(e);
        }
    }
}
