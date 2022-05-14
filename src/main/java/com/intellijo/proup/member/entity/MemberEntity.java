package com.intellijo.proup.member.entity;

import com.intellijo.proup.common.entity.BaseEntity;
import com.intellijo.proup.member.dto.MemberDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "TB_MEMBER")
@NoArgsConstructor
@Getter
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_IDX")
    private Long id;
    @Column(name = "MEMBER_ID")
    private String name;
    @Column(name = "MEMBER_PW")
    private String pw;
    @Column(name = "MEMBER_ADR")
    private String adr;
    @Column(name = "MEMBER_NICKNAME")
    private String nickname;

    // 테스트용 빌더
    @Builder
    MemberEntity (String name, String pw, String adr, String nickname) {
        this.name = name;
        this.pw = pw;
        this.adr = adr;
        this.nickname = nickname;
    }

    @Builder(builderClassName = "toEntityBuilder", builderMethodName = "toEntityBuilder")
    MemberEntity (MemberDTO memberDTO){
        this.name = memberDTO.getName();
        this.pw = memberDTO.getPw();
        this.adr = memberDTO.getAdr();
        this.nickname = memberDTO.getNickname();
    }



}
