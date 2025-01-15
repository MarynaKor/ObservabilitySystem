package com.example.Counter_Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ProjectController {
    private final ProjectServiceClient projectServiceClient;

    public ProjectController(ProjectServiceClient projectServiceClient) {
        this.projectServiceClient = projectServiceClient;
    }

    @GetMapping("/counter/{projectId}")
    public Mono<String> createProject(@PathVariable String projectId) {
        return projectServiceClient.getProjectById(projectId)
                .map(project -> "so many days have passed: " + project.countedDaysFromTheBeginning() + " in the Project " + project.getDescription());
    }
}
