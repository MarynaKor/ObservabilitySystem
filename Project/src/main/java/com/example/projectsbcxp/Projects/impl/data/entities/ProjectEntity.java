package com.example.projectsbcxp.Projects.impl.data.entities;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="project")
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private Timestamp startingAt;
    private Timestamp endingAt;
    private int activeProjectDays;

    @OneToMany(mappedBy = "project")
    @ToString.Exclude
    @FieldNameConstants.Exclude
    private List<PersonProjectPositionEntity> personProjectPosition;
}