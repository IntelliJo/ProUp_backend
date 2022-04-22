package com.intellijo.proup.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
//모든 테이블에  공통적으로 들어가는 Entity
public class BaseEntity {
    //SecurityContext에서 현재 로그인한 ID를 가져와 자동으로 넣어줌
    @CreatedBy
    @Column(name = "CONSTRUCTOR", updatable = false)
    private String constructor;

    @LastModifiedBy
    @Column(name = "MODIFIER")
    private String modifier;

    //데이터의 등록시간과 수정시간과 같이 자동으로 추가되고 변경되어야하는 칼럼 처리해주기 위한 Entity
    @CreatedDate
    @Column(name = "REG_DATE", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "MOD_DATE")
    private LocalDateTime modDate;
}
