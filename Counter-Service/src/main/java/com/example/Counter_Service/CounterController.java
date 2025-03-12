package com.example.Counter_Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;


import java.util.ArrayList;
import java.util.List;


@Configuration
@RestController
public class CounterController {
    private final Tracer tracer;
    private static final Logger log = LoggerFactory.getLogger(CounterController.class);
    private final ProjectServiceClient projectServiceClient;
    private final PositionServiceClient positionServiceClient;
    private final Counter ProjectCounter;
    private final Counter PositionCounter;

    public CounterController(ProjectServiceClient projectServiceClient, ProjectInfoAutoConfiguration projectInfoAutoConfiguration, PositionServiceClient positionServiceClient, MeterRegistry registry, OpenTelemetry openTelemetry) {
        this.projectServiceClient = projectServiceClient;
        this.positionServiceClient = positionServiceClient;
        this.ProjectCounter =  Counter.builder("update_project_in_total").
                description("How many projects were changed and incremented").
                register(registry);
        this.PositionCounter =  Counter.builder("update_position_in_total").
                description("How many positions were changed and incremented").
                register(registry);
        this.tracer = openTelemetry.getTracer("application");
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
        projectUpdate.setActiveProjectDays(newActiveDays);
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
    @EventListener(ApplicationReadyEvent.class)
    public List<PersonProjectPosition> getAndUpdatePositions(){
        log.info("Request to update all Positions");
        List<PersonProjectPosition> positions = positionServiceClient.getAllPositions();
        Integer[] iDS = positions.stream().map(PersonProjectPosition::getId).toArray(Integer[]::new);
        List<PersonProjectPosition> changedPosition = new ArrayList<>();
        for (Integer id : iDS) {
            log.info("Updating the days for the Position with ID: {}", id);
            PositionCounter.increment();
            changedPosition.add(getAndUpdatePosition(id));
        }
        return changedPosition;
    }
    @GetMapping("/overwrite/projects")
    @EventListener(ApplicationReadyEvent.class)
    @Bean
    public List<Project> getAndUpdateProjects(){
        log.info("Request to update all Projects");
        //tracer.spanBuilder("updatingProjects");
        List<Project> projects = projectServiceClient.getAllProjects();
        Integer[] iDS = projects.stream().map(Project::getId).toArray(Integer[]::new);
        List<Project> changedProjects = new ArrayList<>();
        for (Integer id : iDS) {
            log.info("Updating the days for the Project with ID: {}", id);
            ProjectCounter.increment();
            changedProjects.add(getAndUpdateProject(id));
        }
        return changedProjects;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("hello world, I have just started up");
    }
}







