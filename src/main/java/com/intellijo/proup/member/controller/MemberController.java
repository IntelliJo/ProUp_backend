package com.intellijo.proup.member.controller;

import com.intellijo.proup.member.dto.MemberDTO;
import com.intellijo.proup.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/proup/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 추가
     * @param memberDTO
     * @return MemberDTO
     */
    @PostMapping()
    public ResponseEntity<MemberDTO.MemberResponseDTO> memberJoin(@RequestBody @Valid MemberDTO memberDTO){
        return new ResponseEntity<>(memberService.memberJoin(memberDTO), HttpStatus.OK);
    }

    /**
     * 회원 삭제
     * @param id
     * @return Long id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> memberDelete(@PathVariable("id") Long id){
        return new ResponseEntity<>(memberService.memberDelete(id), HttpStatus.OK);
    }

    /**
     * 회원 상세페이지
     * @param id
     * @return MemberDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO.MemberResponseDTO> memberFind(@PathVariable("id") Long id){
        return new ResponseEntity<>(memberService.memberFind(id), HttpStatus.OK);
    }

    /**
     * 회원 정보 수정
     * @param id
     * @param memberRequest
     * @return MemberDTO
     */
    @PatchMapping("/{id}")
    public ResponseEntity<MemberDTO.MemberResponseDTO> memberUpdate(@PathVariable("id") Long id, @Valid MemberDTO.MemberRequestDTO memberRequest){
        return new ResponseEntity<>(memberService.memberUpdate(id, memberRequest), HttpStatus.OK);
    }


}
