package com.intellijo.proup.member.service;

import com.intellijo.proup.member.dto.MemberDTO;
import com.intellijo.proup.member.entity.MemberEntity;
import com.intellijo.proup.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    private static String nullError = "해당 아이디 회원이 없습니다. id = %s";

    /**
     * 회원 추가
     *
     * @param memberDTO
     * @return memberDTO
     */
    public MemberDTO.MemberResponseDTO memberJoin(MemberDTO.MemberRequestDTO memberRequestDTO){

        MemberEntity memberSave = memberRepository.save(memberRequestDTO.memberEntityConverter());
        return MemberDTO.MemberResponseDTO.toDTOBuilder()
                .memberEntity(memberSave).build();
    }

    /**
     * 회원 삭제
     * @param memberId
     * @return memberId
     */
    public boolean memberDelete(Long memberId){
        boolean result = true;
        try {
            memberRepository.deleteById(memberId);
        } catch (IllegalArgumentException | EmptyResultDataAccessException exception) {
            result = false;
        }
        return result;
    }

    /**
     * 회원 단일 조회
     * @param memberId
     * @return MemberDTO
     */
    public MemberDTO.MemberResponseDTO memberFind(Long memberId){
        MemberEntity findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException(String.format(nullError,memberId)));
        return MemberDTO.MemberResponseDTO.toDTOBuilder().memberEntity(findMember).build();
    }

    /**
     * 회원 수정
     * @param id
     * @param memberDTO
     * @return MemberDTO
     */
    public MemberDTO.MemberResponseDTO memberUpdate(Long id, MemberDTO.MemberRequestDTO memberDTO){

        MemberEntity findMember = memberRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("없는 회원...."));
        findMember.memberUpdate(memberDTO.getAdr(), memberDTO.getNickname());

        return MemberDTO.MemberResponseDTO.toDTOBuilder().memberEntity(findMember).build();
    }

    




}
