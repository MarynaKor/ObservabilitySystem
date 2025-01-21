package com.example.Counter_Service;
import com.example.Counter_Service.Project;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProjectController {
    private final ProjectServiceClient projectServiceClient;

    public ProjectController(ProjectServiceClient projectServiceClient) {
        this.projectServiceClient = projectServiceClient;
    }

    @GetMapping("/counter/{projectId}")
    public Mono<String> createProject(@PathVariable Integer projectId) {
        return projectServiceClient.getProjectById(projectId)
                .map(project -> "Amount of active days in the project: " + project.countedDaysFromTheBeginning() + " in the Project:  " + project.getTitle());
    }

    @GetMapping("/counter/projects")
    public Flux<String> getAllProjects() {
        return projectServiceClient.getAllProjects().map(project -> "This project has been active for these amount of days: " + project.countedDaysFromTheBeginning() + " in the Project:  " + project.getTitle() +"\n");
    }

    @GetMapping("/overwrite/{projectId}")
    public Mono<Project> getAndUpdateProject(@PathVariable Integer projectId) {
        Mono<Project> projectUpdate = projectServiceClient.getProjectById(projectId);
        Mono<Long> newActiveDays = projectServiceClient.getProjectById(projectId).map(Project::countedDaysFromTheBeginning);
        Project updatedProject = projectUpdate.block();
        assert updatedProject != null;
        updatedProject.setActive_project_days(newActiveDays.block());
        return projectServiceClient.updateProject(updatedProject);
    }

    @GetMapping("/overwrite/projects")
    public void getAndUpdateProjects() {
         int[] projectIds = new int[]{projectServiceClient.getProjectsId()};
         for(Integer i : projectIds) {
             getAndUpdateProject(i);
         }
    }


    //@GetMapping("/overwrite/projects")
    //public Flux<String> getAndUpdateAllProjects() {
        //Flux<Project> allProjects= projectServiceClient.getAllProjects().blo;
        //allProjects.map(Project::countedDaysFromTheBeginning);
        //Flux<java. lang. Integer> projectIds = allProjects.map(Project::getId); // convert id's into an array!!


}
