package com.intellijo.proup.project;

import com.intellijo.proup.project.dto.StackDTO;
import com.intellijo.proup.project.entity.StackEntity;
import com.intellijo.proup.project.repository.StackRepository;
import com.intellijo.proup.project.service.StackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StackServiceTests {
    @Autowired
    private StackService service;
    @Autowired
    private StackRepository stackRepository;

    @BeforeEach
    void dummy_stack() {
        stackRepository.saveAll(
                List.of(StackEntity.builder().name("Java").description("Java 만세!").build(),
                        StackEntity.builder().name("Spring").description("최고의 framework").build())
        );
    }

    @Test
    void 아이디_리스트로_stack_조회() {
        List<StackDTO> stackListByIds = service.getStackListByIds(List.of(1L, 2L));

        assertThat(stackListByIds.get(0).getId()).isEqualTo(1L);
        assertThat(stackListByIds.get(0).getName()).isEqualTo("Java");
        assertThat(stackListByIds.get(1).getId()).isEqualTo(2L);
        assertThat(stackListByIds.get(1).getName()).isEqualTo("Spring");

    }
}
