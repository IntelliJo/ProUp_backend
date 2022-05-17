package com.intellijo.proup.member.entity;

import com.intellijo.proup.common.entity.BaseEntity;
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
    MemberEntity (Long id, String name, String pw, String adr, String nickname) {
        this.id = id;
        this.name = name;
        this.pw = pw;
        this.adr = adr;
        this.nickname = nickname;
    }

    public void memberUpdate(String adr, String nickname){
        this.adr = adr;
        this.nickname = nickname;
    }

}
