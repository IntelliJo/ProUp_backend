package com.intellijo.proup.project.repository;

import com.intellijo.proup.project.entity.StackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StackRepository extends JpaRepository<StackEntity, Long> {
}
