package com.example.Counter_Service;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
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
    private Timestamp starting_at;
    private Timestamp ending_at;
    private long active_project_days;
    private List<PersonProjectPosition> personProjectPosition;


    //shoudl I set the varaible immediatly equal the equation or leave as a return for the function
    public long countedDaysFromTheBeginning() {
        LocalDate starDate = starting_at.toLocalDateTime().toLocalDate();
        if (ending_at == null) {
            Timestamp currentDate = new Timestamp(System.currentTimeMillis());
            LocalDate endDate = currentDate.toLocalDateTime().toLocalDate();
            return ChronoUnit.DAYS.between(starDate, endDate);
        }else {
            LocalDate endDate = ending_at.toLocalDateTime().toLocalDate();
            return ChronoUnit.DAYS.between(starDate, endDate);
        }
     }



}
