package com.example.Counter_Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

//constructed after that but I needed a parameter url to be able to change that easily
//https://docs.spring.io/spring-boot/docs/2.1.1.RELEASE/reference/html/boot-features-webclient.html
//https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/web-reactive.html#webflux-client-retrieve

@Service
public class ProjectServiceClient {

    private final RestClient restClient;
    //private final WebClient webClient;
    private final String projectServiceUrl;

    /*public ProjectServiceClient(WebClient.Builder webClientBuilder, @Value("${project.service.url}") String projectServiceUrl) {
        this.webClient= webClientBuilder.baseUrl(projectServiceUrl).build();
        this.projectServiceUrl = projectServiceUrl;
    }*/
    RestClient defaultClient = RestClient.create();

    public ProjectServiceClient(RestClient.Builder restClientBuilder, @Value("${project.service.url}") String projectServiceUrl) {
        this.restClient = restClientBuilder.baseUrl(projectServiceUrl).build();
        this.projectServiceUrl = projectServiceUrl;
    }

    public Project getProjectById(Integer projectId) {
        return restClient.get()
                .uri(projectServiceUrl + "/" + projectId)
                .retrieve()
                .body(Project.class);//arbeite mit stream
    }

    public List getAllProjects() {
        return (restClient.get()
                .uri(projectServiceUrl + "/projects")
                .retrieve()
                .body(List.class));

    }

/*    public Iterable<Integer> getAllProjectsIds() throws NoSuchFieldException {
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
    }*/
}