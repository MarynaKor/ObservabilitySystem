/*package com.example.Counter_Service;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Builder
public class ProjectsMapper {

    public List<Projects> getAllProjects() {
        List<ProjectEntity> projectsEntity = projectRepository.findAll();
        return projectsEntity.stream()
                .map(projectsMapper::fromEntity)
                .toList();
    }
}
*/