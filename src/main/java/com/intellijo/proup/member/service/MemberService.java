package com.intellijo.proup.member.service;

import com.intellijo.proup.member.dto.MemberDTO;
import com.intellijo.proup.member.entity.MemberEntity;
import com.intellijo.proup.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    public MemberDTO memberJoin(MemberDTO memberDTO){
        MemberEntity memberEntity = memberRepository.save(
                MemberEntity
                        .toEntityBuilder()
                        .memberDTO(memberDTO)
                        .build()
        );
        return MemberDTO
                .toDTOBuilder()
                .memberEntity(memberEntity)
                .build();
    }




}
