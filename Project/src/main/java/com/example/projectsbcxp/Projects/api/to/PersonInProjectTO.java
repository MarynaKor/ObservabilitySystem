package com.example.projectsbcxp.Projects.api.to;
import lombok.Builder;

import java.sql.Timestamp;


@Builder(toBuilder = true)
public record  PersonInProjectTO(Integer id,
                                 String name,
                                 String title,
                                 Timestamp startInProjectDate,
                                 Timestamp endInProjectDate,
                                 Integer daysActive){
}
