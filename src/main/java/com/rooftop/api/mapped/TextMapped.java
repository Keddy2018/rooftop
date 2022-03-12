package com.rooftop.api.mapped;

import com.rooftop.api.dto.TextResponseAllDto;
import com.rooftop.api.model.Text;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TextMapped {

    public TextResponseAllDto textResponseAllDto2Text(Text text) {
        TextResponseAllDto textResponseAllDto = new TextResponseAllDto();
        textResponseAllDto.setId(text.getId());
        textResponseAllDto.setChars(text.getChars());
        textResponseAllDto.setHash(text.getHashCode());
        textResponseAllDto.setResult(linkedHashMap2String(text.getResult()));
        return textResponseAllDto;

    }

    public List<TextResponseAllDto> listTextResponseAllDto2ListText(List<Text> listText) {
        List<TextResponseAllDto> listTextResponseAllDto = new ArrayList();
        for (Text text : listText) {
            if (text.getIsActive()) {
                TextResponseAllDto textResponseAllDto = new TextResponseAllDto();
                listTextResponseAllDto.add(textResponseAllDto2Text(text));
            }
        }
        return listTextResponseAllDto;
    }

    public String string2LinkedHashMap(LinkedHashMap<String, Integer> link) {
        String result = link.toString();
        result = result.replace("{", "");
        result = result.replace("}", "");
        result = result.replace(", ", ",");
        return result;
    }

    private LinkedHashMap<String, Integer> linkedHashMap2String(String result) {
        LinkedHashMap linked = new LinkedHashMap();
        String[] listResult = result.split(",");
        for (String string : listResult) {
            String[] subResult = string.split("=");
            String key = subResult[0];
            Integer value = Integer.valueOf(subResult[1]);
            linked.put(key, value);
        }
        return linked;
    }
}
