package com.example.Counter_Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProjectServiceClient {

    private final WebClient.Builder webClientBuilder;
    private final String projectServiceUrl;

    public ProjectServiceClient(WebClient.Builder webClientBuilder, @Value("${project.service.url}") String projectServiceUrl) {
        this.webClientBuilder = webClientBuilder;
        this.projectServiceUrl = projectServiceUrl;
    }

    public Mono<Project> getProjectById(String projectId) {
        return webClientBuilder.build()
                .get()
                .uri(projectServiceUrl + "/" + projectId)
                .retrieve()
                .bodyToMono(Project.class);
    }
}