package com.intellijo.proup.project.dto;

import com.intellijo.proup.project.entity.ProjectEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

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
        private List<Long> stackList;

        @Builder(builderMethodName = "toDTOBuilder", builderClassName = "toDTOBuilder")
        ProjectInfoDTO(ProjectEntity project) {
            this.id = project.getId();
            this.name = project.getName();
            this.description = project.getDescription();
            this.stackList = project.getStacks() != null ? project.getStacks().stream().map(projectStackEntity -> projectStackEntity.getStack().getId()).collect(Collectors.toList()) : null;
        }

        public ProjectEntity convertEntity() {
            return ProjectEntity.builder()
                    .id(this.id)
                    .name(this.name)
                    .description(this.description)
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectRequestDTO {
        @NotBlank(message = "이름은 null일 수 없습니다.")
        private String name;
        private String description;
        @NotEmpty(message = "프로젝트에 stack은 반드시 추가되어야합니다.")
        private List<Long> stackList;
    }
}
