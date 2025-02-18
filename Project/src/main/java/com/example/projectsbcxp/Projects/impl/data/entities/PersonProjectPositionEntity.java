package com.example.projectsbcxp.Projects.impl.data.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;


import java.sql.Timestamp;
//no underscore in java only in sql -> Camelcase
@Entity
@Table(name="personprojectposition")
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PersonProjectPositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp startInProject;
    private Timestamp endInProject;
    private int daysActive;


    //fragen Ralph oder Stefan ob sie wissen ob das auch gut funktoniert
    //ausprobieren ohne da ich die entity nicht nach außen gebe
    @ManyToOne
    @JoinColumn(name = "person_id")
   /* @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)*/
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = "project_id")
   /* @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)//ist das der Name der Column von der anderen Entity?*/
    private ProjectEntity project;


}


