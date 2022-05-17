package com.intellijo.proup.member;

import com.intellijo.proup.member.dto.MemberDTO;
import com.intellijo.proup.member.service.MemberService;
import com.intellijo.proup.project.entity.ProjectEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;

    MemberDTO testMember(){
        return MemberDTO.builder()
                .id(1L)
                .pw("pwTest")
                .name("nameTest")
                .adr("adrTest")
                .nickname("nickNameTest")
                .build();

    }

    @Test
    void 회원_가입_테스트(){
        //when
        MemberDTO.MemberResponseDTO joinMember = memberService.memberJoin(testMember());
        //then
        Assertions.assertThat(joinMember.getName()).isEqualTo(testMember().getName());
        Assertions.assertThat(joinMember.getNickname()).isEqualTo(testMember().getNickname());
    }

    @Test
    void 회원_삭제_테스트(){
        //given
        MemberDTO.MemberResponseDTO joinMember = memberService.memberJoin(testMember());
        //when
        boolean result = memberService.memberDelete(joinMember.getId());
        //then
        assertTrue(result);
    }

    @Test
    void 회원_정보_수정_테스트(){
        //given
        MemberDTO.MemberResponseDTO joinMember = memberService.memberJoin(testMember());
        MemberDTO.MemberRequestDTO updateInfo = new MemberDTO.MemberRequestDTO("updateADR", "updateNick");

        // when
        MemberDTO.MemberResponseDTO updateMember = memberService.memberUpdate(joinMember.getId(), updateInfo);
        // then
        Assertions.assertThat(updateMember.getAdr()).isEqualTo(updateInfo.getAdr());
        Assertions.assertThat(updateMember.getNickname()).isEqualTo(updateInfo.getNickname());

    }

    @Test
    void 회원_상세조회_테스트(){
        //given
        MemberDTO.MemberResponseDTO savedMember = memberService.memberJoin(testMember());
        //when
        MemberDTO.MemberResponseDTO findMember = memberService.memberFind(savedMember.getId());
        //then
        Assertions.assertThat(findMember.getName()).isEqualTo(savedMember.getName());
        Assertions.assertThat(findMember.getAdr()).isEqualTo(savedMember.getAdr());
        Assertions.assertThat(findMember.getNickname()).isEqualTo(savedMember.getNickname());
    }


}
