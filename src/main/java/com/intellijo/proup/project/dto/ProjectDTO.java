package com.intellijo.proup.project.dto;

import com.intellijo.proup.project.entity.ProjectEntity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectInfoDTO {
        private Long id;
        private String name;
        private String description;
        private List<StackDTO> stackList;

        @Builder(builderMethodName = "toDTOBuilder", builderClassName = "toDTOBuilder")
        ProjectInfoDTO(ProjectEntity project) {
            this.id = project.getId();
            this.name = project.getName();
            this.description = project.getDescription();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectRequestDTO {
        @NotNull
        private String name;
        private String description;
        private List<StackDTO> stackList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectUpdateDTO {
        private String name;
        private String description;
        private List<StackDTO> stackList;
    }

}
