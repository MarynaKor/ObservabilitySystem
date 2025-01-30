package com.example.Counter_Service;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor

public class Project {
    private int id;
    private String title;
    private String description;
    private Timestamp startingAt;
    private Timestamp endingAt;
    private long activeProjectDays;
    private List<PersonProjectPosition> personProjectPosition;


    //shoudl I set the varaible immediatly equal the equation or leave as a return for the function
    public long countedDaysFromTheBeginning() {
        LocalDate starDate = startingAt.toLocalDateTime().toLocalDate();
        if (endingAt == null) {
            Timestamp currentDate = new Timestamp(System.currentTimeMillis());
            LocalDate endDate = currentDate.toLocalDateTime().toLocalDate();
            return ChronoUnit.DAYS.between(starDate, endDate);
        }else {
            LocalDate endDate = endingAt.toLocalDateTime().toLocalDate();
            return ChronoUnit.DAYS.between(starDate, endDate);
        }
     }



}
