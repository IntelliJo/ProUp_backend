package com.intellijo.proup.project.service;

import com.intellijo.proup.project.dto.ProjectDTO;
import com.intellijo.proup.project.entity.ProjectEntity;
import com.intellijo.proup.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    /**
     * 프로젝트를 생성하는 메소드
     *
     * @param projectDTO
     * @return
     */
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        ProjectEntity projectEntity = ProjectEntity.toEntityBuilder().projectDTO(projectDTO).build();

        ProjectEntity createdProject = projectRepository.save(projectEntity);

        return ProjectDTO.toDTOBuilder().project(createdProject).build();
    }

    /**
     * index로 프로젝트를 조회하는 메소드
     *
     * @param index
     * @return
     */
    public ProjectDTO getProjectById(long index) {
        Optional<ProjectEntity> projectEntity = projectRepository.findById(index);

        return ProjectDTO.toDTOBuilder().
                project(projectEntity.orElseThrow(NullPointerException::new)).
                build();
    }

    /**
     * index로 프로젝트를 삭제하는 메소드
     *
     * @param index
     * @return
     */
    public Boolean deleteProjectById(long index) {
        boolean result;
        try {
            projectRepository.deleteById(index);
            result = true;
        } catch (IllegalArgumentException | EmptyResultDataAccessException exception) {
            result = false;
        }
        return result;
    }

}
