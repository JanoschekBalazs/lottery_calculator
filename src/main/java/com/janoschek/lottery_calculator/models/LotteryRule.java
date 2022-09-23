package com.janoschek.lottery_calculator.models;

import lombok.Value;

@Value
public class LotteryRule {

    String name;
    Object value;

    public static LotteryRule of(String name, Object value) {
        return new LotteryRule(name, value);
    }

    public <T> T as(Class<T> type) {
        return type.cast(value);
    }

}
