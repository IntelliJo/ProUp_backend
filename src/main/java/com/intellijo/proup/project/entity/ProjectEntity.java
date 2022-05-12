package com.intellijo.proup.project.entity;

import com.intellijo.proup.common.entity.BaseEntity;
import com.intellijo.proup.project.dto.ProjectDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TB_PROJECT")
@NoArgsConstructor
@Getter
public class ProjectEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectStackEntity> stacks = new ArrayList<>();

    @Builder
    ProjectEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Builder(builderMethodName = "toEntityBuilder", builderClassName = "toEntityBuilder")
    ProjectEntity(ProjectDTO.ProjectRequestDTO projectDTO) {
        this.name = projectDTO.getName();
        this.description = projectDTO.getDescription();
    }

    public ProjectEntity updateEntity(ProjectDTO.ProjectUpdateDTO updateDTO) {
        this.name = updateDTO.getName();
        this.description = updateDTO.getDescription();
        return this;
    }

}
