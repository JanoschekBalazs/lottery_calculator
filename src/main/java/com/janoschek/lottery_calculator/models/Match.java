package com.janoschek.lottery_calculator.models;

import lombok.Value;

@Value
public class Match {

    short id;
    long impossibility;

    public double getPossibility() {
        return 1/((double) impossibility);
    }
}
