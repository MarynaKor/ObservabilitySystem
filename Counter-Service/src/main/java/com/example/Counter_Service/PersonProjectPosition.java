package com.example.Counter_Service;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor

public class PersonProjectPosition {
    private int id;
    private String name;
    private String title;
    private Timestamp startInProjectDate;
    private Timestamp endInProjectDate;
    private long daysActive;

    public long countedDaysForPosition() {
        LocalDate startDate = startInProjectDate.toLocalDateTime().toLocalDate();
        if (endInProjectDate == null) {
            Timestamp currentDate = new Timestamp(System.currentTimeMillis());
            LocalDate endDate = currentDate.toLocalDateTime().toLocalDate();
            return ChronoUnit.DAYS.between(startDate, endDate);
        }else {
            LocalDate endDate = endInProjectDate.toLocalDateTime().toLocalDate();
            return ChronoUnit.DAYS.between(startDate, endDate);
        }
    }

}
