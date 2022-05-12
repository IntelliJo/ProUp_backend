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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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

    ProjectDTO.ProjectInfoDTO dummy_project() {
        return ProjectDTO.ProjectInfoDTO.builder()
                .id(1L)
                .name("project")
                .description("this is sample project description")
                .build();
    }

    ProjectDTO.ProjectInfoDTO dummy_update_project() {
        return ProjectDTO.ProjectInfoDTO.builder()
                .id(1L)
                .name("update project name")
                .description("this is update sample project description")
                .build();
    }

    Map<String, String> inputMap() {
        Map<String, String> input = new HashMap<>();
        input.put("name", "project");
        input.put("description", "this is sample project description");

        return input;
    }

    Map<String, String> updateInputMap() {
        Map<String, String> input = new HashMap<>();
        input.put("name", "update project name");
        input.put("description", "this is update sample project description");

        return input;
    }

    @Test
    void 프로젝트_추가_컨트롤러_테스트() throws Exception {
        //given
        given(projectService.createProject(any())).willReturn(dummy_project());

        mockMvc.perform(post("/api/v1/proup/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputMap())))
                .andExpect(status().isOk())
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

    @Test
    void 프로젝트_리스트_조회_컨트롤러_테스트() throws Exception {
        //given
        given(projectService.getProjectList(any())).willReturn(new PageImpl<>(List.of(dummy_project(), dummy_project(), dummy_project(), dummy_project(), dummy_project())));

        mockMvc.perform(get("/api/v1/proup/project")
                        .queryParam("page", "0")
                        .queryParam("size", "5")
                        .queryParam("sort", "id,desc"))
                .andExpect(status().isOk())
                .andDo(document("getProjectList",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("size").description("Size of the page you want to retrieve, defaults to 20."),
                                parameterWithName("page").description("Page you want to retrieve, 0 indexed and defaults to 0."),
                                parameterWithName("sort").description("Properties that should be sorted by in the format property,property(,ASC|DESC). Default sort direction is ascending. Use multiple sort parameters if you want to switch directions, e.g. ?sort=firstname&sort=lastname,asc.")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("content").description("Actual items").type(JsonFieldType.ARRAY),
                                fieldWithPath("content.[].id").description("프로젝트의 index").type(JsonFieldType.NUMBER),
                                fieldWithPath("content.[].name").description("프로젝트의 이름").type(JsonFieldType.STRING),
                                fieldWithPath("content.[].description").description("프로젝트의 설명").type(JsonFieldType.STRING),
                                fieldWithPath("totalElements").description("Total count.").type(JsonFieldType.NUMBER),
                                fieldWithPath("totalPages").description("Total pages with current page size.").type(JsonFieldType.NUMBER),
                                fieldWithPath("last").description("If this page is the last one.").type(JsonFieldType.BOOLEAN),
                                fieldWithPath("numberOfElements").description("Actual size of content array (number of items)").type(JsonFieldType.NUMBER),
                                fieldWithPath("first").description("If this page is the first one.").type(JsonFieldType.BOOLEAN),
                                fieldWithPath("sort").description("Sort information object").type(JsonFieldType.OBJECT),
                                fieldWithPath("size").description("Requested size of the page.").type(JsonFieldType.NUMBER),
                                fieldWithPath("number").description("Page number").type(JsonFieldType.NUMBER)
                        )
                ));
    }

    @Test
    void 프로젝트_조회_컨트롤러_테스트() throws Exception {
        //given
        given(projectService.getProjectById(anyLong())).willReturn(dummy_project());

        mockMvc.perform(get("/api/v1/proup/project/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(document("getProjectById",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("id").description("조회 할 코드의 id")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("프로젝트의 index").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("프로젝트의 이름").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("프로젝트의 설명").type(JsonFieldType.STRING)
                        )
                ));
    }

    @Test
    void 프로젝트_삭제_컨트롤러_테스트() throws Exception {
        //given
        given(projectService.deleteProjectById(anyLong())).willReturn(true);

        mockMvc.perform(delete("/api/v1/proup/project/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(document("deleteProjectById",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("id").description("조회 할 코드의 id")
                        )
                ));
    }

    @Test
    void 프로젝트_수정_컨트롤러_테스트() throws Exception {
        //given
        given(projectService.updateProject(anyLong(), any())).willReturn(dummy_update_project());

        mockMvc.perform(patch("/api/v1/proup/project/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateInputMap())))
                .andExpect(status().isOk())
                .andDo(document("updateProject",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("id").description("조회 할 코드의 id")
                        ),
                        relaxedRequestFields(
                                fieldWithPath("name").description("프로젝트의 이름").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("프로젝트의 설명").type(JsonFieldType.STRING)
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("프로젝트의 index").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("프로젝트의 이름").type(JsonFieldType.STRING),
                                fieldWithPath("description").description("프로젝트의 설명").type(JsonFieldType.STRING)
                        )
                ));
    }

}
