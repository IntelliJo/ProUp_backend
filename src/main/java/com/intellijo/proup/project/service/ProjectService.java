package com.intellijo.proup.project.service;

import com.intellijo.proup.project.dto.ProjectDTO;
import com.intellijo.proup.project.entity.ProjectEntity;
import com.intellijo.proup.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    /**
     * 프로젝트를 생성하는 메소드
     *
     * @param projectEntity
     * @return
     */
    public ProjectDTO createProject(ProjectEntity projectEntity) {
        ProjectEntity createdProject = projectRepository.save(projectEntity);

        return ProjectDTO.toDTOBuilder().project(createdProject).build();
    }

    public ProjectDTO getProjectById(long index) {
        Optional<ProjectEntity> projectEntity = projectRepository.findById(index);

        return ProjectDTO.toDTOBuilder().
                project(projectEntity.orElseThrow(NullPointerException::new)).
                build();
    }

    public Boolean deleteProjectById(long index) {
        boolean result;
        try {
            projectRepository.deleteById(index);
            result = true;
        }catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
            result = false;
        }
        return result;
    }
}
