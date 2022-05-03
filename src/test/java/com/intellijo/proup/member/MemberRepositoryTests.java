package com.intellijo.proup.member;

import com.intellijo.proup.member.entity.MemberEntity;
import com.intellijo.proup.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 멤버_등록_테스트() {
        MemberEntity memberEntity = MemberEntity.builder()
                .name("김연훈")
                .pw("test123")
                .adr("강남구")
                .nickname("야눈의 이것저것")
                .build();

        MemberEntity savedMember = memberRepository.save(memberEntity);

        List<MemberEntity> all = memberRepository.findAll();

        all.get(0);
    }
}
