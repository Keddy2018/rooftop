package com.rooftop.api.dto;

import lombok.Data;

@Data
public class FormatErrorDto {
    
    private Boolean error;
    
    private String message;
    
    private Integer code;
    
}
