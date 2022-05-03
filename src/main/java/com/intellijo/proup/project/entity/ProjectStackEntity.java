package com.intellijo.proup.project.entity;

import com.intellijo.proup.common.entity.BaseEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "TB_PROJECT_STACK")
@NoArgsConstructor
public class ProjectStackEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ProjectEntity project;
    @ManyToOne
    private StackEntity stack;

    @Builder
    ProjectStackEntity (ProjectEntity project, StackEntity stack) {
        this.project = project;
        this.stack = stack;
    }
}
