package com.example.Counter_Service;

import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;



@RestController
public class ProjectController {
    private final ProjectServiceClient projectServiceClient;
    private final PositionServiceClient positionServiceClient;

    public ProjectController(ProjectServiceClient projectServiceClient, ProjectInfoAutoConfiguration projectInfoAutoConfiguration, PositionServiceClient positionServiceClient) {
        this.projectServiceClient = projectServiceClient;
        this.positionServiceClient = positionServiceClient;
    }

    @GetMapping("/counter/{projectId}")
    public String createProject(@PathVariable Integer projectId) {
        Project projectById = projectServiceClient.getProjectById(projectId);
        return "Amount of active days in the project: " + projectById.countedDaysFromTheBeginning() + " in the Project:  " + projectById.getTitle();
    }
    //Objekt ausgeben and look up hash map

    @GetMapping("/counter/projects")
    public String getAllProjects() {
        List<Project> projects =  projectServiceClient.getAllProjects();
        String response = "";
        for (Project project : projects) {
           response =  response.concat("Amount of active days in the project: " + project.countedDaysFromTheBeginning() + " in the Project:  " + project.getId());
        }

        //projects.forEach(project -> response.concat("Amount of active days in the project: " + project.countedDaysFromTheBeginning() + " in the Project:  " + project.getId()));
        return response;
    }
    @GetMapping("/overwrite/project/{projectId}")
    public Project getAndUpdateProject(@PathVariable Integer projectId) {
        Project projectUpdate = projectServiceClient.getProjectById(projectId);
        long newActiveDays = projectUpdate.countedDaysFromTheBeginning();
        projectUpdate.setActive_project_days(newActiveDays);
        return projectServiceClient.updateProject(projectUpdate);
    }
    /*@GetMapping("/overwrite/position/{positionId}")
    public PersonProjectPosition getAndUpdatePosition(@PathVariable Integer positionId) {
        return positionServiceClient.getPositionById(positionId);
    }*/

    @GetMapping("/overwrite/position/{positionId}")
    public PersonProjectPosition getAndUpdatePosition(@PathVariable Integer positionId) {
        PersonProjectPosition positionUpdate = positionServiceClient.getPositionById(positionId);
        long newActiveDays = positionUpdate.countedDaysForPosition();
        positionUpdate.setDaysActive(newActiveDays);
        return positionServiceClient.updatePosition(positionUpdate);
    }
    @GetMapping("/overwrite/positions")
    public List<PersonProjectPosition> getAndUpdatePositions(){
        List<PersonProjectPosition> positions = positionServiceClient.getAllPositions();
        Integer[] iDS = positions.stream().map(PersonProjectPosition::getId).toArray(Integer[]::new);
        List<PersonProjectPosition> changedPosition = new ArrayList<>();
        for (Integer id : iDS) {
            changedPosition.add(getAndUpdatePosition(id));
        }
        return changedPosition;
    }
   
    @GetMapping("/overwrite/projects")
    public List<Project> getAndUpdateProjects(){
        List<Project> projects = projectServiceClient.getAllProjects();
        Integer[] iDS = projects.stream().map(Project::getId).toArray(Integer[]::new);
        List<Project> changedProjects = new ArrayList<>();
        for (Integer id : iDS) {
            changedProjects.add(getAndUpdateProject(id));
        }
        return changedProjects;
    }
}






