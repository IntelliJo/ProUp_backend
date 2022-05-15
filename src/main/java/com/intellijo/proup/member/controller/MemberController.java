package com.intellijo.proup.member.controller;

import com.intellijo.proup.member.dto.MemberDTO;
import com.intellijo.proup.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/proup/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<MemberDTO> memberJoin(@RequestBody @Valid MemberDTO memberDTO){
        return new ResponseEntity<>(memberService.memberJoin(memberDTO), HttpStatus.OK);
    }



}
