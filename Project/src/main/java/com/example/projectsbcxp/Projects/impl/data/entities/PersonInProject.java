package com.example.projectsbcxp.Projects.impl.data.entities;

import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PersonInProject {
    @Id
    private int id;
    private String name;
    private String title;
    private Timestamp startInProject;
    private Timestamp endInProject;
    private int daysActive;

}
