package com.intellijo.proup.project.entity;

import com.intellijo.proup.common.entity.BaseEntity;
import com.intellijo.proup.project.dto.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TB_PROJECT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProjectEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProjectStackEntity> stacks = new ArrayList<>();

    public void addStack(ProjectStackEntity projectStackEntity) {
        stacks.add(projectStackEntity);
    }

    @Builder(builderMethodName = "toEntityBuilder", builderClassName = "toEntityBuilder")
    ProjectEntity(ProjectDTO.ProjectRequestDTO projectDTO) {
        this.name = projectDTO.getName();
        this.description = projectDTO.getDescription();
    }

    public void updateEntity(ProjectDTO.ProjectUpdateDTO updateDTO) {
        this.name = updateDTO.getName();
        this.description = updateDTO.getDescription();
    }

}
