package com.rooftop.api.mapped;

import com.rooftop.api.dto.TextResponseAllDto;
import com.rooftop.api.model.Text;
import org.springframework.stereotype.Component;


@Component
public class TextMapped {
    
    public TextResponseAllDto textResponseAllDto2Text(Text text){
        TextResponseAllDto textResponseAllDto = new TextResponseAllDto();
        textResponseAllDto.setId(text.getId());
        textResponseAllDto.setChars(text.getChars());
        textResponseAllDto.setHash(text.getHashCode());
        textResponseAllDto.setResult(text.getMapa());
        return textResponseAllDto;
        
    }
}
