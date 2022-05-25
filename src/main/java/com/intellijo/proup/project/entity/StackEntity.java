package com.intellijo.proup.project.entity;

import com.intellijo.proup.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TB_STACK")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StackEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "stack", fetch = FetchType.LAZY)
    private List<ProjectStackEntity> projects = new ArrayList<>();

}
