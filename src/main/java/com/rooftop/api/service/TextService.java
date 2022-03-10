package com.rooftop.api.service;

import com.rooftop.api.dto.TextAddDto;
import java.util.HashMap;





public interface TextService {
    
    Object addText(TextAddDto textAddDto);
    
    HashMap deleteText(Long id);
    
}
