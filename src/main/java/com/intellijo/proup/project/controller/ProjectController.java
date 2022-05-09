package com.intellijo.proup.project.controller;

import com.intellijo.proup.project.dto.ProjectDTO;
import com.intellijo.proup.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService service;

    /**
     * 프로젝트 등록 컨트롤러
     *
     * @param projectDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<ProjectDTO> insertProject(@RequestBody ProjectDTO projectDTO) {
        return new ResponseEntity<>(service.createProject(projectDTO), HttpStatus.OK);
    }

//    @GetMapping()
//    public ResponseEntity<List<ProjectDTO>> getProjectList() {
//        return new ResponseEntity<>()
//    }
}
