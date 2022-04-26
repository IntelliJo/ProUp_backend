package com.intellijo.proup.project.entity;

import com.intellijo.proup.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TB_STACK")
@NoArgsConstructor
@Getter
public class StackEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "stack", fetch = FetchType.LAZY)
    private List<ProjectStackEntity> projects = new ArrayList<>();

    @Builder
    StackEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
