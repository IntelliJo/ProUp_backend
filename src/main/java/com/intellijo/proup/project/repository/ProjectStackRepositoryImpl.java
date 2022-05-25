package com.intellijo.proup.project.repository;

import com.intellijo.proup.project.entity.ProjectEntity;
import com.intellijo.proup.project.entity.QProjectEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.intellijo.proup.project.entity.QProjectStackEntity.projectStackEntity;

@Repository
public class ProjectStackRepositoryImpl {
    private final JPAQueryFactory jpaQueryFactory;

    public ProjectStackRepositoryImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }


    public List<ProjectEntity> getProject() {
        return jpaQueryFactory
                .selectFrom(QProjectEntity.projectEntity)
                .where()
                .fetch();
    }

    public void deleteByProjectId(Long id) {
        jpaQueryFactory
                .delete(projectStackEntity)
                .where(projectStackEntity.project.id.eq(id))
                .execute();
    }
}
