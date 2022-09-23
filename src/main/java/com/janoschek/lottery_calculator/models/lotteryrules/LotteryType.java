package com.janoschek.lottery_calculator.models.lotteryrules;

import com.janoschek.lottery_calculator.models.LotteryRule;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum LotteryType {
    LOTTERY_5(Stream.of(
        LotteryRule.of("poolSize", 90),
        LotteryRule.of("combinationSize", 5)
    ).collect(Collectors.toUnmodifiableMap(LotteryRule::getName, item -> item)));

    private final Map<String, LotteryRule> rules;

    LotteryType(Map<String, LotteryRule> rules) {
        this.rules = rules;
    }

    public Map<String, LotteryRule> getRules() {
        return rules;
    }
}
