package com.example.projectsbcxp.Projects.access.controllers;
import com.example.projectsbcxp.Projects.api.PersonInterface;
import com.example.projectsbcxp.Projects.api.ProjectInterface;
import com.example.projectsbcxp.Projects.api.to.PersonInProjectTO;
import com.example.projectsbcxp.Projects.api.to.PersonTO;
import com.example.projectsbcxp.Projects.api.to.ProjectsTO;
import com.example.projectsbcxp.Projects.impl.data.entities.PersonProjectPositionEntity;
import com.example.projectsbcxp.Projects.impl.data.entities.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j

public class ProjectController {
    private final ProjectInterface projectService;
    private final PersonInterface personService;



    @GetMapping
    List<ProjectsTO> getAllProjects(){
        return projectService.getAllProjects() ;
    }

    @GetMapping("/projects")
    public List<ProjectsTO> getActiveProjects(){
        return projectService.getActiveProjects() ;
    }

    @PostMapping("/projects")
    public ProjectEntity createProjects(@RequestBody ProjectsTO projectsTO){
        return projectService.addProject(projectsTO);
    }

    @GetMapping("/project/{id}")
    public ProjectsTO getProjectById(@PathVariable int id){
        return projectService.getProjectById(id);
    }

    @GetMapping("/position/{id}")
    public PersonInProjectTO getPositionById(@PathVariable int id){
        return personService.getPositionById(id);
    }

    //not sure if I will ever need this call...reconsider this in the future!
    @GetMapping("/personsActive")
    public List<PersonTO> getActivePersonsInProjects(){
        return personService.getActivePersons() ;
    }

    @GetMapping("/positions")
    List<PersonInProjectTO> getAllActivePositions(){
        return personService.getActivePositionsInProject() ;
    }

    @PutMapping("/update/project")
    public ProjectsTO updateProjects(@RequestBody ProjectsTO projectsTO){
        return projectService.updateProject(projectsTO);
    }

    //here
    @PutMapping("/update/position")
    public PersonInProjectTO updatePositions(@RequestBody PersonInProjectTO personInProjectTO){
        return personService.updatePosition(personInProjectTO);
    }
}


