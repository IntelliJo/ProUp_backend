package com.intellijo.proup.project.service;

import com.intellijo.proup.project.dto.ProjectDTO;
import com.intellijo.proup.project.dto.StackDTO;
import com.intellijo.proup.project.entity.ProjectEntity;
import com.intellijo.proup.project.entity.ProjectStackEntity;
import com.intellijo.proup.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final StackService stackService;

    /**
     * 프로젝트를 생성하는 메소드
     *
     * @param projectDTO 프로젝트dto
     * @return
     */
    public ProjectDTO.ProjectInfoDTO createProject(ProjectDTO.ProjectRequestDTO projectDTO) {
        List<StackDTO> projectStackList = stackService.getStackListByIds(projectDTO.getStackList());
        ProjectEntity projectEntity = projectRepository.save(ProjectEntity.toEntityBuilder().projectDTO(projectDTO).build());

        projectStackList.forEach(stackDTO ->
                projectEntity.addStack(
                        ProjectStackEntity.builder()
                                .project(projectEntity)
                                .stack(stackDTO.convertEntity())
                                .build()
                ));

        return ProjectDTO.ProjectInfoDTO.toDTOBuilder().project(projectEntity).build();
    }


    /**
     * index로 프로젝트를 조회하는 메소드
     *
     * @param index 프로젝트의 인덱스
     * @return
     */
    public ProjectDTO.ProjectInfoDTO getProjectById(long index) {
        ProjectEntity projectEntity = projectRepository.findById(index).orElseThrow(IllegalArgumentException::new);

        return ProjectDTO.ProjectInfoDTO.toDTOBuilder().
                project(projectEntity).
                build();
    }

    /**
     * 프로젝트 리스트를 조회하는 메소드
     *
     * @param pageable 페이지네이션을 위한 파라미터
     * @return
     */
    public Page<ProjectDTO.ProjectInfoDTO> getProjectList(Pageable pageable) {
        Page<ProjectEntity> projectEntityPage = projectRepository.findAll(pageable);

        return projectEntityPage.map(project -> ProjectDTO.ProjectInfoDTO.toDTOBuilder().project(project).build());
    }

    public ProjectDTO.ProjectInfoDTO updateProject(Long projectId, ProjectDTO.ProjectUpdateDTO projectDTO) {

        ProjectEntity projectEntity = projectRepository.findById(projectId).orElseThrow(IllegalArgumentException::new);

        return ProjectDTO.ProjectInfoDTO.toDTOBuilder().project(projectEntity.updateEntity(projectDTO)).build();
    }

    /**
     * index로 프로젝트를 삭제하는 메소드
     *
     * @param index 프로젝트의 index
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
