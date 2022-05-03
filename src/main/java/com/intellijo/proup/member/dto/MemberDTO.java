package com.intellijo.proup.member.dto;

import com.intellijo.proup.member.entity.MemberEntity;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class MemberDTO {
    private Long id;
    private String name;
    private String pw;
    private String adr;
    private String nickname;

    @Builder(builderClassName = "toDTOBuilder", builderMethodName = "toDTOBuilder")
    MemberDTO(MemberEntity memberEntity){
        this.name = memberEntity.getName();
        this.pw = memberEntity.getPw();
        this.adr = memberEntity.getAdr();
        this.nickname = memberEntity.getNickname();
    }
}
