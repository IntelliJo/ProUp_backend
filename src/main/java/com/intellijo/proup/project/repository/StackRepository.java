package com.intellijo.proup.project.repository;

import com.intellijo.proup.project.entity.StackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StackRepository extends JpaRepository<StackEntity, Long> {
}
