package com.intellijo.proup.member.controller;

import com.intellijo.proup.member.dto.MemberDTO;
import com.intellijo.proup.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<MemberDTO> memberJoin(@RequestBody MemberDTO memberDTO){
        return new ResponseEntity<MemberDTO>(memberService.createMember(memberDTO), HttpStatus.OK);
    }

}
