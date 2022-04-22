package com.intellijo.proup.project.entity;

import com.intellijo.proup.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_STACK")
public class StackEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "project")
    private List<ProjectStackEntity> projects = new ArrayList<>();
}
