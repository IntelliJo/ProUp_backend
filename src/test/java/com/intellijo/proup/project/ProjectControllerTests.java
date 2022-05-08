package com.intellijo.proup.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellijo.proup.project.controller.ProjectController;
import com.intellijo.proup.project.dto.ProjectDTO;
import com.intellijo.proup.project.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
@ActiveProfiles(profiles = "test")
class ProjectControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProjectService projectService;

    ProjectDTO dummy_project() {
        return ProjectDTO.builder()
                .id(1L)
                .name("project")
                .description("this is sample project description")
                .build();
    }

    Map<String, String> inputMap() {
        Map<String, String> input = new HashMap<>();
        input.put("name", "project");
        input.put("description", "this is sample project description");

        return input;
    }

    @Test
    void 프로젝트_추카_컨트롤러_테스트() throws Exception {
        //given
        given(projectService.createProject(any())).willReturn(dummy_project());

        mockMvc.perform(post("/api/v1/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputMap()))
                ).andExpect(status().isOk())
                .andDo(document("createProject",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        relaxedResponseFields(
                                fieldWithPath("id").description("프로젝트의 index").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("프로젝트의 이름").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("프로젝트의 설명").type(JsonFieldType.STRING)
                        )
                ));
    }
}
