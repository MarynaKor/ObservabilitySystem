package com.example.projectsbcxp.Projects.api.to;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.List;

/**
 * TODO
 *
 * @param id
 */
    @Builder
    public record ProjectsTO(Integer id,
                             String title,
                             String description,
                             Timestamp startingAt,
                             Timestamp endingAt,
                             Integer activeProjectDays,
                             List<PersonInProjectTO> personen) {
}





