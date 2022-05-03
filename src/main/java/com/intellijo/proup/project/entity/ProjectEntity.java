package com.intellijo.proup.project.entity;

import com.intellijo.proup.common.entity.BaseEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TB_PROJECT")
@NoArgsConstructor
public class ProjectEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "stack")
    private List<ProjectStackEntity> stacks = new ArrayList<>();

    @Builder
    ProjectEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
