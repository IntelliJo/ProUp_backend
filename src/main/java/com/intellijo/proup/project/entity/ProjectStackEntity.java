package com.intellijo.proup.project.entity;

import com.intellijo.proup.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "TB_PROJECT_STACK")
public class ProjectStackEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id")
    private ProjectEntity project;
    @ManyToOne
    @JoinColumn(name = "id")
    private StackEntity stack;
}
