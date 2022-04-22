package com.intellijo.proup.project.entity;

import com.intellijo.proup.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PROJECT")
public class ProjectEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long  id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "stack")
    private List<ProjectStackEntity> stacks = new ArrayList<>();
}
