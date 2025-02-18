package com.example.projectsbcxp.Projects.impl.business;
import com.example.projectsbcxp.Projects.api.to.ProjectsTO;
import com.example.projectsbcxp.Projects.impl.data.entities.ProjectEntity;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.projectsbcxp.tecbase.utils.nullcheck.ReturnNullOnNullParameter;

@Service
@RequiredArgsConstructor
public class ProjectsMapper {

    private final PersonMapper personMapper;
    @ReturnNullOnNullParameter
    public ProjectsTO fromEntity(ProjectEntity project) {
        return ProjectsTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .startingAt(project.getStartingAt())
                .endingAt(project.getEndingAt())
                .activeProjectDays(project.getActiveProjectDays())
                .personen(project.getPersonProjectPosition().stream()
                        .map(personMapper::fromPersonProjectPosition)
                        .toList())

                .build();
    }

    @ReturnNullOnNullParameter
    public ProjectsTO fromEntityWithOutPositions(ProjectEntity project) {
        return ProjectsTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .startingAt(project.getStartingAt())
                .endingAt(project.getEndingAt())
                .activeProjectDays(project.getActiveProjectDays())
                .build();
    }


    @ReturnNullOnNullParameter
    public ProjectEntity toEntity(@Nullable ProjectsTO project) {
        assert project != null;
        return ProjectEntity.builder()
                .id(project.id())
                .title(project.title())
                .description(project.description())
                .startingAt(project.startingAt())
                .endingAt(project.endingAt())
                .activeProjectDays(project.activeProjectDays())
                .build();
    }



}
