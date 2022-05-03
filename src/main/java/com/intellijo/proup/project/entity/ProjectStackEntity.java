package com.intellijo.proup.project.entity;

import com.intellijo.proup.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "TB_PROJECT_STACK")
@Getter
@NoArgsConstructor
public class ProjectStackEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private ProjectEntity project;
    @ManyToOne(fetch = FetchType.LAZY)
    private StackEntity stack;

    @Builder
    ProjectStackEntity (ProjectEntity project, StackEntity stack) {
        this.project = project;
        this.stack = stack;
    }
}
