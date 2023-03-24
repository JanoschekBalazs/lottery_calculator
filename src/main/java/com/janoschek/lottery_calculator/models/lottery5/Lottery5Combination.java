package com.janoschek.lottery_calculator.models.lottery5;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@NoArgsConstructor
@Document("combinations")
public class Lottery5Combination {

    public static final int SIZE = 5;
    public static final int POOL_SIZE = 90;

    @Id
    @EqualsAndHashCode.Exclude
    @Setter(value = AccessLevel.NONE)
    private String id;

    @Setter(value = AccessLevel.NONE)
    @NonNull
    private int[] numbers;

    public Lottery5Combination(Collection<Integer> numbers) {
        this.numbers = numbers.stream().mapToInt(i -> i).toArray();
    }

    public int match(Lottery5Combination other) {

        int i = 0, j = 0, intersection = 0;
        while (i < numbers.length && j < other.numbers.length) {
            if (numbers[i] < other.numbers[j]) {
                i++;
            } else if (other.numbers[j] < numbers[i]) {
                j++;
            } else {
                intersection++;
                j++;
                i++;
            }
        }
        return intersection;
    }
}
