package com.janoschek.lottery_calculator.models;

public interface Draw<T extends LotteryTicket<T>> {

    T getTicket();
}
