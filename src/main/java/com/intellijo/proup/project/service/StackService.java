package com.intellijo.proup.project.service;

import com.intellijo.proup.project.dto.StackDTO;
import com.intellijo.proup.project.repository.ProjectStackRepository;
import com.intellijo.proup.project.repository.StackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StackService {
    private final StackRepository stackRepository;
    private final ProjectStackRepository projectStackRepository;

    /**
     * id로 stack을 조회해 리턴해주는 메소드
     *
     * @param stackIds
     * @return
     */
    public List<StackDTO> getStackListByIds(List<Long> stackIds) {
        return stackRepository.findAllById(stackIds).stream().map(stack ->
                StackDTO.toDTOBuilder().stackEntity(stack).build()).collect(Collectors.toList());
    }

}
