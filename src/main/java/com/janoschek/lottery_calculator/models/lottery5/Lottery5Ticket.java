package com.janoschek.lottery_calculator.models.lottery5;

import com.janoschek.lottery_calculator.models.LotteryTicket;
import com.janoschek.lottery_calculator.models.Match;
import com.janoschek.lottery_calculator.models.lotteryrules.Lottery5Rules;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Document("combinations_lottery5")
public class Lottery5Ticket implements LotteryTicket<Lottery5Ticket> {

    private static final Lottery5Rules rules = Lottery5Rules.get();
    private static final Map<Integer, Match> matches = new HashMap<>();

    @Id
    @EqualsAndHashCode.Exclude
    private String id;

    @NonNull private Set<Integer> numbers;

    public Lottery5Ticket() { }

    public Lottery5Ticket(Collection<Integer> numbers) {
        this.numbers = new HashSet<>(numbers);
    }

    @Override
    public Match match(Lottery5Ticket other) {
        var intersection = new HashSet<>(numbers);
        intersection.retainAll(other.numbers);
        return intersection.size();
    }
}
