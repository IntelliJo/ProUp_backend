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
        this.id = memberEntity.getId();
        this.name = memberEntity.getName();
        this.pw = memberEntity.getPw();
        this.adr = memberEntity.getAdr();
        this.nickname = memberEntity.getNickname();
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MemberRequestDTO{
        private String name;
        private String pw;
        private String adr;    // 회원 주소
        private String nickname;   // 회원 닉네임


        public MemberEntity memberEntityConverter(){
            return MemberEntity.builder()
                    .pw(this.pw)
                    .name(this.name)
                    .adr(this.adr)
                    .nickname(this.nickname)
                    .build();
        }


    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MemberResponseDTO{
        private Long id;
        private String name;   // 회원 이름
        private String adr;    // 회원 주소
        private String nickname;   // 회원 닉네임

        @Builder(builderClassName = "toDTOBuilder", builderMethodName = "toDTOBuilder")
        MemberResponseDTO(MemberEntity memberEntity){
            this.id = memberEntity.getId();
            this.name = memberEntity.getName();
            this.adr = memberEntity.getAdr();
            this.nickname = memberEntity.getNickname();
        }

    }

}

