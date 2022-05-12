package com.intellijo.proup.project;

import com.intellijo.proup.project.dto.ProjectDTO;
import com.intellijo.proup.project.entity.ProjectEntity;
import com.intellijo.proup.project.repository.ProjectRepository;
import com.intellijo.proup.project.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles(profiles = "test")
class ProjectServiceTests {
    @Autowired
    private ProjectService service;
    @Autowired
    private ProjectRepository projectRepository;

    List<ProjectEntity> create_dummy_projects() {
        ProjectEntity projectEntity1 = ProjectEntity.builder()
                .name("test project1")
                .description("test1")
                .build();

        ProjectEntity projectEntity2 = ProjectEntity.builder()
                .name("test project2")
                .description("test2")
                .build();

        ProjectEntity projectEntity3 = ProjectEntity.builder()
                .name("test project3")
                .description("test3")
                .build();

        ProjectEntity projectEntity4 = ProjectEntity.builder()
                .name("test project4")
                .description("test4")
                .build();

        ProjectEntity projectEntity5 = ProjectEntity.builder()
                .name("test project5")
                .description("test5")
                .build();

        ProjectEntity projectEntity6 = ProjectEntity.builder()
                .name("test project6")
                .description("test6")
                .build();

        return projectRepository.saveAll(List.of(projectEntity1, projectEntity2, projectEntity3, projectEntity4, projectEntity5, projectEntity6));
    }

    ProjectEntity create_dummy_project() {
        return projectRepository.save(ProjectEntity.builder().name("testProject").description("test").build());
    }

    @Test
    void 프로젝트_등록_테스트() {
        ProjectDTO.ProjectRequestDTO projectDTO = ProjectDTO.ProjectRequestDTO.builder().name("test").description("test").build();
        ProjectDTO.ProjectInfoDTO createdProject = service.createProject(projectDTO);

        assertThat(createdProject.getName()).isEqualTo(projectDTO.getName());
        assertThat(createdProject.getDescription()).isEqualTo(projectDTO.getDescription());
    }

    @Test
    void 프로젝트_조회_테스트() {
        ProjectDTO.ProjectInfoDTO createdProject = service.createProject(ProjectDTO.ProjectRequestDTO.builder().name("test").description("test").build());
        ProjectDTO.ProjectInfoDTO projectDTO = service.getProjectById(createdProject.getId());

        assertThat(createdProject.getId()).isEqualTo(projectDTO.getId());
    }

    @Test
    void 프로젝트_리스트_조회_테스트() {
        List<ProjectEntity> dummy_projects = create_dummy_projects();
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
        Page<ProjectDTO.ProjectInfoDTO> projectList = service.getProjectList(pageable);

        assertThat(projectList.get()).hasSize(5);
    }

    @Test
    void 프로젝트_수정_테스트() {
        ProjectEntity dummy_project = create_dummy_project();

        ProjectDTO.ProjectUpdateDTO updateDTO = ProjectDTO.ProjectUpdateDTO.builder().
                name("updateProejct").description("test").build();

        service.updateProject(dummy_project.getId(), updateDTO);

        ProjectDTO.ProjectInfoDTO findByIdProject = service.getProjectById(dummy_project.getId());

        assertThat(updateDTO.getName()).isEqualTo(findByIdProject.getName());
        assertThat(updateDTO.getDescription()).isEqualTo(findByIdProject.getDescription());

    }

    @Test
    void 프로젝트_삭제_테스트() {
        ProjectEntity dummy_project = create_dummy_project();
        Boolean result = service.deleteProjectById(dummy_project.getId());

        assertTrue(result);
    }


}
