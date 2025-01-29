package com.example.Counter_Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

//constructed after that but I needed a parameter url to be able to change that easily
//https://docs.spring.io/spring-boot/docs/2.1.1.RELEASE/reference/html/boot-features-webclient.html
//https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/web-reactive.html#webflux-client-retrieve

@Service
public class ProjectServiceClient {

    private final RestClient restClient;

    private final String projectServiceUrl;

    RestClient defaultClient = RestClient.create();

    public ProjectServiceClient(RestClient.Builder restClientBuilder, @Value("${project.service.url}") String projectServiceUrl) {
        this.restClient = restClientBuilder.baseUrl(projectServiceUrl).build();
        this.projectServiceUrl = projectServiceUrl;
    }

    public Project getProjectById(Integer projectId) {
        return restClient.get()
                .uri(projectServiceUrl + "/project/" + projectId)
                .retrieve()
                .body(Project.class);//arbeite mit stream
    }

    // first array maybe later a List
    public List<Project> getAllProjects() {
        Project[] projects = restClient.get()
                .uri(projectServiceUrl + "/projects")
                .retrieve()
                .body(Project[].class);
        assert projects != null;
        return Arrays.asList(projects);

    }

    public Project updateProject(Project project) {
        return restClient.put()
                .uri(projectServiceUrl + "/update/project")
                .contentType(MediaType.APPLICATION_JSON)
                .body(project)
                .retrieve()
                .body(Project.class);
    }

}