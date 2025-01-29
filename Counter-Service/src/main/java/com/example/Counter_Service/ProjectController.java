package com.example.Counter_Service;
import com.example.Counter_Service.Project;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.StringBuilder.*;


@RestController
public class ProjectController {
    private final ProjectServiceClient projectServiceClient;

    public ProjectController(ProjectServiceClient projectServiceClient, ProjectInfoAutoConfiguration projectInfoAutoConfiguration) {
        this.projectServiceClient = projectServiceClient;
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
        /*HashMap<Integer, Long> projectCountedDays = new HashMap<>();
        projects.forEach(project -> projectCountedDays.put(project.getId(),project.countedDaysFromTheBeginning()));
        */
        String response = "";
        for (Project project : projects) {
           response =  response.concat("Amount of active days in the project: " + project.countedDaysFromTheBeginning() + " in the Project:  " + project.getId());
        }

        //projects.forEach(project -> response.concat("Amount of active days in the project: " + project.countedDaysFromTheBeginning() + " in the Project:  " + project.getId()));
        return response;
    }
    @GetMapping("/overwrite/{projectId}")
    public Project getAndUpdateProject(@PathVariable Integer projectId) {
        Project projectUpdate = projectServiceClient.getProjectById(projectId);
        long newActiveDays = projectUpdate.countedDaysFromTheBeginning();
        projectUpdate.setActive_project_days(newActiveDays);
        return projectServiceClient.updateProject(projectUpdate);
    }

    @GetMapping("/overwrite/projects")
    public List<Project> getAndUpdateProjects(){
        List<Project> projects = projectServiceClient.getAllProjects();
        Integer[] iDS = projects.stream().map(Project::getId).toArray(Integer[]::new);
        List<Project> changedProjects = new ArrayList<>();;
        for (Integer id : iDS) {
            changedProjects.add(getAndUpdateProject(id));
        }
        return changedProjects;
    }
}
    /*

    //Project project
    @GetMapping("/overwrite/projects")
    public List<Project> getAndUpdateProjects() throws NoSuchFieldException {
        List<Project> allUpdatedProjects = new ArrayList<>();
        String aB = null;
        Iterable<Integer> iDS = projectServiceClient.getAllProjectsIds();
        for (Integer id : iDS) {
            /*String bb = Integer.toString(id);
            aB = aB + " und " +  bb;
            Project projectUpdate = projectServiceClient.getProjectById(id);
            long newActiveDays = projectServiceClient.getNewDaysAmount(id);
            assert projectUpdate != null;
            projectUpdate.setActive_project_days(newActiveDays);
            System.out.println(projectUpdate.getActive_project_days());
            allUpdatedProjects.add(projectUpdate);

        }

        return allUpdatedProjects;
    }*/





