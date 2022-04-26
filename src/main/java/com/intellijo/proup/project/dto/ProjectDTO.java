package com.intellijo.proup.project.dto;

import com.intellijo.proup.project.entity.ProjectEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private List<StackDTO> stackList;

    @Builder(builderMethodName = "toDTOBuilder", builderClassName = "toDTOBuilder")
    ProjectDTO (ProjectEntity project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
    }
}
