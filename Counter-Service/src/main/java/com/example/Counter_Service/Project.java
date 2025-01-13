package com.example.Counter_Service;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
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
    private int active_project_days;
    private List<PersonProjectPosition> personProjectPosition;


}
