package com.example.projectsbcxp.Projects.api;
import com.example.projectsbcxp.Projects.api.to.ProjectsTO;
import com.example.projectsbcxp.Projects.impl.data.entities.ProjectEntity;

import java.util.List;

public interface ProjectInterface {


    List<ProjectsTO> getAllProjects();
    ProjectEntity addProject(ProjectsTO project);
    ProjectsTO getProjectById(int id);
    List<ProjectsTO> getActiveProjects();
    ProjectsTO updateProject(ProjectsTO project);
}
