package com.intellijo.proup.project.controller;

import com.intellijo.proup.project.dto.ProjectDTO;
import com.intellijo.proup.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/proup/project")
public class ProjectController {
    private final ProjectService service;

    /**
     * 프로젝트 등록 컨트롤러
     *
     * @param projectDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<ProjectDTO.ProjectInfoDTO> insertProject(@Valid @RequestBody ProjectDTO.ProjectRequestDTO projectDTO) {
        return new ResponseEntity<>(service.createProject(projectDTO), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<ProjectDTO.ProjectInfoDTO>> getProjectList(Pageable pageable) {
        return new ResponseEntity<>(service.getProjectList(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO.ProjectInfoDTO> getProjectById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getProjectById(id), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.deleteProjectById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectDTO.ProjectInfoDTO> updateProjectById(@PathVariable("id") Long id, @Valid @RequestBody ProjectDTO.ProjectUpdateDTO updateDTO) {
        return new ResponseEntity<>(service.updateProject(id, updateDTO), HttpStatus.OK);
    }
}
