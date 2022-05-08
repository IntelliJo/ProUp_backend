package com.intellijo.proup.project;

import com.intellijo.proup.project.dto.ProjectDTO;
import com.intellijo.proup.project.service.ProjectService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectServiceTests {
    @Autowired
    private ProjectService service;

    @Test
    @Order(1)
    void 프로젝트_등록_테스트() {
        ProjectDTO projectDTO = ProjectDTO.builder().name("test").description("test").build();
        ProjectDTO createdProject = service.createProject(projectDTO);

        assertThat(createdProject.getName()).isEqualTo(projectDTO.getName());
        assertThat(createdProject.getDescription()).isEqualTo(projectDTO.getDescription());
    }

    @Test
    @Order(2)
    void 프로젝트_조회_테스트() {
        ProjectDTO projectDTO = service.getProjectById(1L);

        assertThat(projectDTO.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    void query_test() {
        List<ProjectDTO> projectList = service.getProjectList();
    }

    @Test
    void 프로젝트_삭제_테스트() {
        Boolean result = service.deleteProjectById(1L);

        assertTrue(result);

        assertFalse(service.deleteProjectById(1L));
        assertThrows(NullPointerException.class, () -> service.getProjectById(1L));
    }


}
