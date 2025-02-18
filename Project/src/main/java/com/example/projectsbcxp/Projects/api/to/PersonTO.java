package com.example.projectsbcxp.Projects.api.to;
import lombok.Builder;

import java.sql.Timestamp;

@Builder(toBuilder = true)
public record PersonTO(Integer id,
                      String name,
                      String title
                       ) {
}
