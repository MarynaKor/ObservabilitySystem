package com.example.Counter_Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

//constructed after that but I needed a parameter url to be able to change that easily
//https://docs.spring.io/spring-boot/docs/2.1.1.RELEASE/reference/html/boot-features-webclient.html
//https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/web-reactive.html#webflux-client-retrieve

@Service
public class ProjectServiceClient {

    private final WebClient webClient;
    private final String projectServiceUrl;

    public ProjectServiceClient(WebClient.Builder webClientBuilder, @Value("${project.service.url}") String projectServiceUrl) {
        this.webClient= webClientBuilder.baseUrl(projectServiceUrl).build();
        this.projectServiceUrl = projectServiceUrl;

    }

    public Project getProjectById(Integer projectId) {
        return webClient.get()
                .uri(projectServiceUrl + "/" + projectId)
                .retrieve()
                .bodyToMono(Project.class)
                .block(); //arbeite mit stream
    }

    public Flux<Project> getAllProjects() {
        return webClient.get()
                .uri(projectServiceUrl + "/projects")
                .retrieve()
                .bodyToFlux(Project.class);

    }

    public Iterable<Integer> getAllProjectsIds() throws NoSuchFieldException {
        return webClient.get()
                .uri(projectServiceUrl + "/projects")
                .retrieve()
                .bodyToFlux(Project.class)
                .map(Project::getId)
                .toIterable();
    }

    public int getProjectsId() {
        return Objects.requireNonNull(webClient.get()
                        .uri(projectServiceUrl + "/projects")
                        .retrieve()
                        .bodyToFlux(Project.class)
                        .blockFirst()).getId();
    }
    //How to retrieve all the id's and not the first or last one
    //Flux.just(new ArrayList<>())
    //  .flatMap(Flux::fromIterable); and save in a array[]

    public long getNewDaysAmount(Integer projectId) {
        return Objects.requireNonNull(webClient.get()
                        .uri(projectServiceUrl + "/" + projectId)
                        .retrieve()
                        .bodyToMono(Project.class)
                        .block())
                .countedDaysFromTheBeginning();

        //arbeite mit stream
    }

    public Mono<Project> updateProject(Project project){
        return webClient.put()
                .uri(projectServiceUrl + "/update/project")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(project)
                .retrieve()
                .bodyToMono(Project.class);
    }
}