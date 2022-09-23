package com.janoschek.lottery_calculator.models.lotteryrules;

import com.janoschek.lottery_calculator.models.LotteryRule;
import lombok.Value;

@Value
class Lottery5Rules extends LotteryRules {

    public Lottery5Rules() {
        super(
            LotteryRule.of("poolSize", 90),
            LotteryRule.of("combinationSize", 5)
        );
    }
}
