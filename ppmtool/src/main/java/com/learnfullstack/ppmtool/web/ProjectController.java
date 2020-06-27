package com.learnfullstack.ppmtool.web;

import com.learnfullstack.ppmtool.domain.Project;
import com.learnfullstack.ppmtool.services.ProjectService;
import com.learnfullstack.ppmtool.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/api/project", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ValidationErrorService validationErrorService;

    @PostMapping(path = "/add")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = validationErrorService.validateError(bindingResult);
        if (errorMap != null)
            return errorMap;

        Project addedProject = projectService.saveAndUpdateProject(project);
        return new ResponseEntity<Project>(addedProject, HttpStatus.CREATED);
    }

    @GetMapping(path = "/find/{projectIdentifier}")
    public ResponseEntity<?> findProjectByIdentifier(@PathVariable String projectIdentifier) {

        Project project = projectService.findProjectByIdentifier(projectIdentifier);
        return new ResponseEntity<>(project, HttpStatus.OK);

    }

    @GetMapping(path = "/findAll")
    public Iterable<Project> findAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping(path = "/delete/{projectIdentifier}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectIdentifier) {
        projectService.deleteProjectByIdentifier(projectIdentifier);

        return new ResponseEntity<>("Project Id - '" + projectIdentifier.toUpperCase() + "' deleted successfully",
                HttpStatus.OK);
    }


}
