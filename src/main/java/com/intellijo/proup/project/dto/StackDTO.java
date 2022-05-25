package com.intellijo.proup.project.dto;

import com.intellijo.proup.project.entity.StackEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StackDTO {
    private Long id;
    private String name;

    @Builder(builderMethodName = "toDTOBuilder", builderClassName = "toDTOBuilder")
    StackDTO(StackEntity stackEntity) {
        this.id = stackEntity.getId();
        this.name = stackEntity.getName();
    }

    public StackEntity convertEntity() {
        return StackEntity.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
