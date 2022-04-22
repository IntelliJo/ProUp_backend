package com.intellijo.proup.common.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ExceptionResponse {
    private int status;
    private Date timestamp;
    private String message;
    private String details;
}
