package com.intellijo.proup.project.repository;

import com.intellijo.proup_biz.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
