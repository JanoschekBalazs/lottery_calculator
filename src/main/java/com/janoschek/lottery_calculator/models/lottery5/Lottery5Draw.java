package com.janoschek.lottery_calculator.models.lottery5;

import com.janoschek.lottery_calculator.models.Draw;
import com.janoschek.lottery_calculator.models.lotteryrules.Lottery5Rules;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Data
@Document ("history_lottery5")
public class Lottery5Draw implements Draw<Lottery5Ticket> {

    private static final Lottery5Rules rules = Lottery5Rules.get();

    @Id
    @EqualsAndHashCode.Exclude
    private String id;

    @NonNull private short year;
    @NonNull private byte week;
    private LocalDate date;
    private Byte hit5_pcs;
    private Long hit5_price;
    private Short hit4_pcs;
    private Long hit4_price;
    private Integer hit3_pcs;
    private Long hit3_price;
    private Integer hit2_pcs;
    private Long hit2_price;

    @NonNull private Set<Integer> numbers;

    public Lottery5Draw() {
    }

    @Builder
    public Lottery5Draw(@NonNull short year, @NonNull byte week, LocalDate date, Byte hit5_pcs, Long hit5_price,
                        Short hit4_pcs, Long hit4_price, Integer hit3_pcs, Long hit3_price, Integer hit2_pcs,
                        Long hit2_price, @NonNull Set<Integer> numbers) {
        this.year = year;
        this.week = week;
        this.date = date;
        this.hit5_pcs = hit5_pcs;
        this.hit5_price = hit5_price;
        this.hit4_pcs = hit4_pcs;
        this.hit4_price = hit4_price;
        this.hit3_pcs = hit3_pcs;
        this.hit3_price = hit3_price;
        this.hit2_pcs = hit2_pcs;
        this.hit2_price = hit2_price;
        this.numbers = numbers;
    }

    public static Lottery5Draw fromCSVRecord(CSVRecord csvRecord) {
        Lottery5DrawBuilder builder = builder()
            .year(Short.parseShort(csvRecord.get(0)))
            .week(Byte.parseByte(csvRecord.get("week")))
            .date(csvRecord.get("date").equals("") ? null : LocalDate.parse(csvRecord.get("date"),
                DateTimeFormatter.ofPattern("yyyy.MM.dd.")));
        if (builder.year > 1997) {
            builder.hit5_pcs(Byte.parseByte(csvRecord.get("hit5_pcs")))
                .hit5_price(Long.parseLong(csvRecord.get("hit5_price")))
                .hit4_pcs(Short.parseShort(csvRecord.get("hit4_pcs")))
                .hit4_price(Long.parseLong(csvRecord.get("hit4_price")))
                .hit3_pcs(Integer.parseInt(csvRecord.get("hit3_pcs")))
                .hit3_price(Long.parseLong(csvRecord.get("hit3_price")))
                .hit2_pcs(Integer.parseInt(csvRecord.get("hit2_pcs")))
                .hit2_price(Long.parseLong(csvRecord.get("hit2_price")));
        }
        var numbers = new HashSet<Integer>(rules.getRule("poolSize").as(Integer.class));
        for (int i = 1; i <= rules.getRule("poolSize").as(Integer.class); i++) {
            numbers.add(Integer.parseInt(csvRecord.get("n" + i)));
        }
        builder.numbers(numbers);
        return builder.build();
    }

    @Override
    public Lottery5Ticket getTicket() {
        return new Lottery5Ticket(numbers);
    }
}
