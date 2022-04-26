package com.intellijo.proup.project;

import com.intellijo.proup.project.dto.ProjectDTO;
import com.intellijo.proup.project.entity.ProjectEntity;
import com.intellijo.proup.project.service.ProjectService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
        ProjectEntity projectEntity = ProjectEntity.builder()
                .name("샘플 프로젝트")
                .description("샘플 프로젝트의 설명")
                .build();

        ProjectDTO createdProject = service.createProject(projectEntity);

        assertThat(createdProject.getName()).isEqualTo(projectEntity.getName());
        assertThat(createdProject.getDescription()).isEqualTo(projectEntity.getDescription());
    }

    @Test
    @Order(2)
    void 프로젝트_조회_테스트() {
        ProjectDTO projectDTO = service.getProjectById(1L);

        assertThat(projectDTO.getId()).isEqualTo(1L);
    }

    @Test
    void 프로젝트_삭제_테스트() {
        Boolean result = service.deleteProjectById(1L);

        assertTrue(result);

        assertFalse(service.deleteProjectById(1L));
        assertThrows(NullPointerException.class,() -> service.getProjectById(1L));
    }
}
