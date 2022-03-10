package com.rooftop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextPostGetDto {
    
    private Long id;
    
    private String url;
}
