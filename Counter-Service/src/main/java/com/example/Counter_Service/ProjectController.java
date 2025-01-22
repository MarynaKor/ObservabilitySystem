package com.example.Counter_Service;
import com.example.Counter_Service.Project;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.micrometer.core.instrument.Meter;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class ProjectController {
    private final ProjectServiceClient projectServiceClient;
    private final ProjectInfoAutoConfiguration projectInfoAutoConfiguration;

    public ProjectController(ProjectServiceClient projectServiceClient, ProjectInfoAutoConfiguration projectInfoAutoConfiguration) {
        this.projectServiceClient = projectServiceClient;
        this.projectInfoAutoConfiguration = projectInfoAutoConfiguration;
    }

    @GetMapping("/counter/{projectId}")
    public String createProject(@PathVariable Integer projectId) {
        Project projectById = projectServiceClient.getProjectById(projectId);
        return "Amount of active days in the project: " + projectById.countedDaysFromTheBeginning() + " in the Project:  " + projectById.getTitle();
    }

    @GetMapping("/counter/projects")
    public Flux<String> getAllProjects() {
        return projectServiceClient.getAllProjects().map(project -> "This project has been active for these amount of days: " + project.countedDaysFromTheBeginning() + " in the Project:  " + project.getTitle() +"\n");
    }
    //change return type to project
    @GetMapping("/overwrite/{projectId}")
    public Mono<Project> getAndUpdateProject(@PathVariable Integer projectId) {
        Project projectUpdate = projectServiceClient.getProjectById(projectId);
        long newActiveDays = projectServiceClient.getNewDaysAmount(projectId);
        assert projectUpdate != null;
        projectUpdate.setActive_project_days(newActiveDays);
        return projectServiceClient.updateProject(projectUpdate);
    }

    //Project project
    @GetMapping("/overwrite/projects")
    public Flux<Project> getAndUpdateProjects() throws NoSuchFieldException {
        Flux<Project> allUpdatedProjects = null;
        Iterable<Integer> iDS = projectServiceClient.getAllProjectsIds();
        for (Integer id : iDS) {
            Project projectUpdate = projectServiceClient.getProjectById(id);
            long newActiveDays = projectServiceClient.getNewDaysAmount(id);
            assert projectUpdate != null;
            projectUpdate.setActive_project_days(newActiveDays);
            System.out.println(projectUpdate.getActive_project_days());
            allUpdatedProjects = Flux.just(projectUpdate);

        }

        return allUpdatedProjects;
    }




    //@GetMapping("/overwrite/projects")
    //public Flux<String> getAndUpdateAllProjects() {
        //Flux<Project> allProjects= projectServiceClient.getAllProjects().blo;
        //allProjects.map(Project::countedDaysFromTheBeginning);
        //Flux<java. lang. Integer> projectIds = allProjects.map(Project::getId); // convert id's into an array!!


}
