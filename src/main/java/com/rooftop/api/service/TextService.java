package com.rooftop.api.service;

import com.rooftop.api.dto.TextAddDto;
import com.rooftop.api.dto.TextResponseAllDto;
import java.util.HashMap;





public interface TextService {
    
    Object addText(TextAddDto textAddDto);
    
    HashMap deleteText(Long id);
    
    TextResponseAllDto getText(Long id);
    
}
