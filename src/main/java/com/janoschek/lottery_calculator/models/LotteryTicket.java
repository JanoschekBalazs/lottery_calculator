package com.janoschek.lottery_calculator.models;

public interface LotteryTicket<T extends LotteryTicket<T>> {

    Match match(T other);

}
