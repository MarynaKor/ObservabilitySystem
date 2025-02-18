package com.example.projectsbcxp.Projects.impl.business;
import com.example.projectsbcxp.Projects.api.ProjectInterface;
import com.example.projectsbcxp.Projects.api.to.ProjectsTO;
import com.example.projectsbcxp.Projects.impl.data.entities.ProjectEntity;
import com.example.projectsbcxp.Projects.impl.data.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Implementierung der ProjektService Methoden.
 */
@Service
@AllArgsConstructor
@Slf4j
@Builder
public class ProjectsService implements ProjectInterface {
    private ProjectRepository projectRepository;
    private ProjectsMapper projectsMapper;
    /**
     * Liefert die Liste aller Projekt Daten der Datenbank.
     *
     * @return Liste aller gefundenen Zeilen, sonst leere Liste
     */
    @Override
    public List<ProjectsTO> getAllProjects() {
        List<ProjectEntity> projectsEntity = projectRepository.findAll();
        return projectsEntity.stream()
                .map(projectsMapper::fromEntity)
                .toList();
    }
    @Override
    public ProjectEntity addProject(ProjectsTO projectsTO) {
        ProjectEntity newProject = projectsMapper.toEntity(projectsTO);
        projectRepository.save(newProject);
        return newProject;
    }

    // change it to projectsTO return
    @Override
    public ProjectsTO updateProject(ProjectsTO projectsTO){
        ProjectEntity projectToUpdate = projectRepository.findById(projectsTO.id()).orElseThrow();
        projectToUpdate.setActiveProjectDays(projectsTO.activeProjectDays());
        //ProjectEntity updatedProject = projectsMapper.toEntity(projectsTO);
        projectRepository.save(projectToUpdate);
        return projectsMapper.fromEntity(projectToUpdate);

        /*
        PersonProjectPositionEntity personPositionById = personInProjectRepo.findById(personInProjectTO.id()).orElseThrow();
        personPositionById.setDaysActive(personInProjectTO.daysActive());
        personInProjectRepo.save(personPositionById);
        return personMapper.fromPositionEntity(personPositionById);
         */
    }

    @Override
    public ProjectsTO getProjectById(int id) {
        ProjectEntity projectWithId = projectRepository.findById(id).orElseThrow();
        return projectsMapper.fromEntity(projectWithId);
    }

    public List<ProjectsTO> getActiveProjects() {
        List<ProjectEntity> activeProjectsEntity= projectRepository.getActiveProjects();
        return activeProjectsEntity.stream()
                .map(projectsMapper::fromEntity)
                .toList();
    }
}
