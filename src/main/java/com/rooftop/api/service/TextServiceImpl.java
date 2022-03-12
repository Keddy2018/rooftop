package com.rooftop.api.service;

import com.rooftop.api.dto.TextAddDto;
import com.rooftop.api.dto.TextResponseAllDto;
import com.rooftop.api.dto.TextResponseDto;
import com.rooftop.api.exception.NotControlledExeption;
import com.rooftop.api.exception.NotExistRecordExeption;
import com.rooftop.api.mapped.TextMapped;
import com.rooftop.api.model.Text;
import com.rooftop.api.repository.TextRepository;
import static com.rooftop.api.util.Constants.*;
import com.rooftop.api.util.Md5Convert;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TextServiceImpl implements TextService {

    @Autowired
    private TextRepository textRepo;

    @Autowired
    private TextMapped textMapped;

    private LinkedHashMap<String, Integer> mapa;

    private void decodeText(String text, int chars) {
        mapa = new LinkedHashMap();
        text = text.toLowerCase();

        if (chars < 2) {
            chars = DEFAULT_CHARS;
        }
        if (chars >= text.length()) {
            mapa.put(text, 1);

        } else {
            for (int i = 0; i < text.length() - 1; i++) {
                if ((chars + i) > text.length()) {
                    break;
                }
                String subText = text.substring(i, (i + chars));
                int cant = serchEquals(text, subText, chars);
                mapa.put(subText, cant);
            }
        }

    }

    private int serchEquals(String text, String findText, int chars) {
        int cont = 0;
        for (int i = 0; i < text.length() - 1; i++) {
            if ((chars + i) > text.length()) {
                break;
            }
            String subText = text.substring(i, (i + chars));
            if (findText.equals(subText)) {
                cont++;
            }
        }
        return cont;
    }

    private void validNotNullId(Long id) {
        if (id == null) {
            throw new NotControlledExeption(TEXT_NOT_CONTROLLED);
        }
    }

    @Override
    public Object addText(TextAddDto textAddDto) {
        try {
            String url = REQ_MAPP_CLASS + "/";
            Long id;
            int chars = textAddDto.getChars();
            if (chars < 2) {
                chars = DEFAULT_CHARS;
            }
            decodeText(textAddDto.getText(), textAddDto.getChars());
            String hashCode = Md5Convert.getMd5(mapa.toString(), chars);
            Optional<Text> textBd = textRepo.findByHashCode(hashCode);

            if (textBd.isPresent()) {
                id = textBd.get().getId();
            } else {
                Text text = new Text();
                text.setResult(textMapped.string2LinkedHashMap(mapa));
                text.setChars(chars);
                text.setHashCode(hashCode);
                text.setIsActive(Boolean.TRUE);
                Text newText = textRepo.save(text);
                id = newText.getId();
            }
            url = url + id;
            return new TextResponseDto(id, url);

        } catch (NoSuchAlgorithmException ex) {

            return ex.getMessage();

        }

    }

    @Override
    public HashMap deleteText(Long id) {
        validNotNullId(id);
        Optional<Text> text = textRepo.findById(id);
        if (text.isPresent()) {
            Text textDelete = text.get();
            if (!textDelete.getIsActive()) {
                throw new NotExistRecordExeption(TEXT_NOT_FOUND);
            }
            textDelete.setIsActive(Boolean.FALSE);
            textRepo.save(textDelete);
            return new HashMap();
        } else {
            throw new NotExistRecordExeption(TEXT_NOT_FOUND);
        }
    }

    @Override
    public TextResponseAllDto getText(Long id) {
        validNotNullId(id);
        Optional<Text> optionalText = textRepo.findById(id);
        if (optionalText.isPresent()) {
            Text text = optionalText.get();
            if (text.getIsActive()) {
                return textMapped.textResponseAllDto2Text(text);
            } else {
                throw new NotExistRecordExeption(TEXT_NOT_FOUND);
            }
        } else {
            throw new NotExistRecordExeption(TEXT_NOT_FOUND);
        }
    }

    @Override
    public List<TextResponseAllDto> getTextPageable(HashMap<String, String> map) {
        int chars = DEFAULT_CHARS;
        int page = DEFAULT_PAGE;
        int rpp = DEFAULT_RPP;

        if (map.containsKey("chars")) {
            int charsPet = Integer.valueOf(map.get("chars"));
            chars = charsPet < 2 ? DEFAULT_CHARS : charsPet;
        }
        if (map.containsKey("page")) {
            int pagePet = Integer.valueOf(map.get("page"));
            page = pagePet <= 1 ? 0 : pagePet - 1;
        }
        if (map.containsKey("rpp")) {
            int rppPet = Integer.valueOf(map.get("rpp"));
            rpp = rppPet < 10 ? 10 : rppPet > 100 ? 100 : rppPet;
        }
        Pageable pageable = PageRequest.of(page, rpp);
        Optional<List<Text>> optionalList = textRepo.findByChars(chars, pageable);
        return textMapped.listTextResponseAllDto2ListText(optionalList.get());

    }

}
