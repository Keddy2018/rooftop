package com.rooftop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextResponseDto {
    
    private Long id;
    
    private String url;
}
