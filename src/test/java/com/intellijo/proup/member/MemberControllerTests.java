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
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    Map<String, String> inputMap(){
        Map<String, String> input = new HashMap<>();
        input.put("name","nameTest");
        input.put("pw","pwTest");
        input.put("adr","adrTest");
        input.put("nickname","nickNameTest");
        return input;
    }
    @Test
    void 회원_추가() throws Exception{
        given(memberService.memberJoin(any())).willReturn(dummy_member());

        mockMvc.perform(post("/api/v1/proup/member")   // perform get요청을 보내는 것
                .contentType(MediaType.APPLICATION_JSON)        // 컨텐츠 타입은 제이슨
                .content(objectMapper.writeValueAsString(inputMap()))   // 컨텐츠 내용
                ).andExpect(status().isOk())           // andExpect ~하길 기대하는 것
                .andDo(document("memberJoin",    // andDo ~을 실행
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        relaxedResponseFields( //relaxed : 문서의 일부분만 확인해도 되게끔 설정해주는 prefix
                                fieldWithPath("id").description("회원의 index").type(JsonFieldType.NUMBER), //fieldWithPath : 응답의 필드를 기술하기 위한 메소드
                                fieldWithPath("name").description("회원의 이름").type(JsonFieldType.STRING),
                                fieldWithPath("pw").description("회원의 비밀번호").type(JsonFieldType.STRING),
                                fieldWithPath("adr").description("회원의 주소").type(JsonFieldType.STRING),
                                fieldWithPath("nickname").description("회원의 닉네임").type(JsonFieldType.STRING)
                        )
                ));
    }


}
