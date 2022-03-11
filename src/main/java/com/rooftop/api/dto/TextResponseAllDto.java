package com.rooftop.api.dto;

import java.util.Map;
import lombok.Data;


@Data
public class TextResponseAllDto {
    
    private Long id;
    
    private String hash;
    
    private Integer chars;
    
    private Map<String, Integer> result;
}
