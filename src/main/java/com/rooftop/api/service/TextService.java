package com.rooftop.api.service;

import com.rooftop.api.dto.TextAddDto;
import com.rooftop.api.dto.TextResponseAllDto;
import java.util.HashMap;
import java.util.List;






public interface TextService {
    
    Object addText(TextAddDto textAddDto);
    
    HashMap deleteText(Long id);
    
    TextResponseAllDto getText(Long id);
    
    List<TextResponseAllDto> getTextPageable(HashMap<String, String> map);
    
}
