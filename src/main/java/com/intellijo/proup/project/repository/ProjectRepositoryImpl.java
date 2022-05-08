package com.intellijo.proup.project.repository;

import com.intellijo.proup.project.entity.ProjectEntity;
import com.intellijo.proup.project.entity.QProjectEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProjectRepositoryImpl {
    private final JPAQueryFactory jpaQueryFactory;

    public ProjectRepositoryImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }


    public List<ProjectEntity> getProject() {
        return jpaQueryFactory
                .selectFrom(QProjectEntity.projectEntity)
                .where()
                .fetch();
    }
}
