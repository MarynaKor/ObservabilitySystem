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
public class PositionServiceClient {

    private final RestClient restClient;

    private final String projectServiceUrl;

    RestClient defaultClient = RestClient.create();

    public PositionServiceClient(RestClient.Builder restClientBuilder, @Value("${project.service.url}") String projectServiceUrl) {
        this.restClient = restClientBuilder.baseUrl(projectServiceUrl).build();
        this.projectServiceUrl = projectServiceUrl;
    }

    public PersonProjectPosition getPositionById(Integer positionId) {
        return restClient.get()
                .uri(projectServiceUrl + "/position/" + positionId)
                .retrieve()
                .body(PersonProjectPosition.class);//arbeite mit stream
    }

    public PersonProjectPosition updatePosition(PersonProjectPosition personprojectposition) {
        return restClient.put()
                .uri(projectServiceUrl + "/update/position")
                .contentType(MediaType.APPLICATION_JSON)
                .body(personprojectposition)
                .retrieve()
                .body(PersonProjectPosition.class);
    }
    public List<PersonProjectPosition> getAllPositions() {
        PersonProjectPosition[] positions = restClient.get()
                .uri(projectServiceUrl + "/positions")
                .retrieve()
                .body(PersonProjectPosition[].class);
        assert positions != null;
        return Arrays.asList(positions);

    }
}
