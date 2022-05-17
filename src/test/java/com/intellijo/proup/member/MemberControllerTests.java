package com.intellijo.proup.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellijo.proup.member.controller.MemberController;
import com.intellijo.proup.member.dto.MemberDTO;
import com.intellijo.proup.member.service.MemberService;
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
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
@ActiveProfiles(profiles = "test")
public class MemberControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MemberService memberService;

    MemberDTO dummy_member(){
        return MemberDTO.builder()
                .id(1L)
                .name("nameTest")
                .pw("pwTest")
                .adr("adrTest")
                .nickname("nickNameTest")
                .build();
    }

    MemberDTO.MemberResponseDTO memberResponseDTO(){
        return MemberDTO.MemberResponseDTO.builder()
                .name("testName")
                .adr("testAdt")
                .nickname("testNick").build();
    }


    Map<String, String> inputMap(){
        Map<String, String> input = new HashMap<>();
        input.put("adr","adrTest");
        input.put("nickname","nickNameTest");
        return input;
    }
    @Test
    void 회원_추가() throws Exception{
        given(memberService.memberJoin(any())).willReturn(memberResponseDTO());

        mockMvc.perform(post("/api/v1/proup/member")   // perform get요청을 보내는 것
                .contentType(MediaType.APPLICATION_JSON)        // 컨텐츠 타입은 제이슨
                .content(objectMapper.writeValueAsString(dummy_member()))   // 컨텐츠 내용
                ).andExpect(status().isOk())           // andExpect ~하길 기대하는 것
                .andDo(document("memberJoin",    // andDo ~을 실행
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        relaxedRequestFields(
                                fieldWithPath("name").description("회원의 아이디").type(JsonFieldType.STRING),
                                fieldWithPath("adr").description("회원의 주소").type(JsonFieldType.STRING),
                                fieldWithPath("nickname").description("회원의 닉네임").type(JsonFieldType.STRING)
                        ),
                        relaxedResponseFields( //relaxed : 문서의 일부분만 확인해도 되게끔 설정해주는 prefix
                                fieldWithPath("name").description("회원의 아이디").type(JsonFieldType.STRING),
                                fieldWithPath("adr").description("회원의 주소").type(JsonFieldType.STRING),
                                fieldWithPath("nickname").description("회원의 닉네임").type(JsonFieldType.STRING)
                        )
                ));
    }

    @Test
    void 회원_삭제_컨트롤러_테스트() throws Exception {
        // 테스트 상품 저장
        given(memberService.memberDelete(any())).willReturn(true);

        mockMvc.perform(delete("/api/v1/proup/member/{id}", dummy_member().getId())
                ).andExpect(status().isOk())
                .andDo(document("memberDelete",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("id").description("회원의 index")
                        )

                ));
    }

    @Test
    void 회원_단일조회_컨트롤러_테스트() throws Exception {
        given(memberService.memberFind(any())).willReturn(memberResponseDTO());

        mockMvc.perform(get("/api/v1/proup/member/{id}", dummy_member().getId())
                ).andExpect(status().isOk())
                .andDo(document("memberFind",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("id").description("회원의 index")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("name").description("회원의 아이디").type(JsonFieldType.STRING),
                                fieldWithPath("adr").description("회원의 주소").type(JsonFieldType.STRING),
                                fieldWithPath("nickname").description("회원의 닉네임").type(JsonFieldType.STRING)
                        )
                ));
    }

    @Test
    void 회원정보_수정_컨트롤러_테스트() throws Exception {
        given(memberService.memberUpdate(any(), any())).willReturn(memberResponseDTO());

        mockMvc.perform(patch("/api/v1/proup/member/{id}", dummy_member().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputMap()))
                ).andExpect(status().isOk())
                .andDo(document("memberUpdate",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("id").description("회원의 index")
                        ),
                        relaxedRequestFields(
                                fieldWithPath("adr").description("회원의 주소").type(JsonFieldType.STRING),
                                fieldWithPath("nickname").description("회원의 닉네임").type(JsonFieldType.STRING)
                        ),
                        relaxedResponseFields(
                                fieldWithPath("name").description("회원의 아이디").type(JsonFieldType.STRING),
                                fieldWithPath("adr").description("회원의 주소").type(JsonFieldType.STRING),
                                fieldWithPath("nickname").description("회원의 닉네임").type(JsonFieldType.STRING)
                        )
                ));

    }


}
