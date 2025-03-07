package com.example.projectsbcxp.Projects.access.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthController {

    @Value( "${spring.application.name}" )
    private String appName;

    @GetMapping("/health")
    public String health() {
        return appName + " is up and running!";
    }
}