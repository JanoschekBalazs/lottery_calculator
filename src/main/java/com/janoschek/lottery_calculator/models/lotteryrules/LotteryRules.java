package com.janoschek.lottery_calculator.models.lotteryrules;

import com.janoschek.lottery_calculator.models.LotteryRule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LotteryRules {

    @Setter (value = AccessLevel.NONE)
    @Getter (value = AccessLevel.PROTECTED)
    private final Map<String, LotteryRule> rules;

    protected LotteryRules(LotteryRule... rules) {
        this.rules = Stream.of(rules).collect(Collectors.toUnmodifiableMap(LotteryRule::getName, item -> item));
    }

    public LotteryRule getRule(String name) {
        return rules.get(name);
    }
}
