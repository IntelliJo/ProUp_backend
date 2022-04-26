package com.intellijo.proup.project.entity;

import com.intellijo.proup.common.entity.BaseEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "TB_PROJECT_STACK")
@NoArgsConstructor
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

    @Builder
    ProjectStackEntity (ProjectEntity project, StackEntity stack) {
        this.project = project;
        this.stack = stack;
    }
}
