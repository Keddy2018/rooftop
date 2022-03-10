package com.rooftop.api.service;

import com.rooftop.api.dto.FormatErrorDto;
import com.rooftop.api.dto.TextAddDto;
import com.rooftop.api.dto.TextPostGetDto;
import com.rooftop.api.model.Text;
import com.rooftop.api.repository.TextRepository;
import static com.rooftop.api.util.Constants.REQ_MAPP_CLASS;
import com.rooftop.api.util.Md5Convert;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextServiceImpl implements TextService {

    @Autowired
    private TextRepository textRepo;

    private HashMap<String, Integer> mapa;

    @Override
    public Object addText(TextAddDto textAddDto) {
        try {
            String url = REQ_MAPP_CLASS + "/";
            Long id = 0L;
            int chars = textAddDto.getChars();
            decodeText(textAddDto.getText(), textAddDto.getChars());
            String hashCode = Md5Convert.getMd5(mapa.toString(), chars);
            Optional<Text> textBd = textRepo.findByHashCode(hashCode);

            if (textBd.isPresent()) {
                id = textBd.get().getId();
            } else {
                Text text = new Text();
                text.setMapa(mapa);
                text.setChars(chars);
                text.setHashCode(hashCode);
                text.setIsActive(Boolean.TRUE);
                Text newText = textRepo.save(text);
                id = newText.getId();
            }
            url = url + id;
            return new TextPostGetDto(id, url);

        } catch (NoSuchAlgorithmException ex) {

            return ex.getMessage();

        }

    }

    private void decodeText(String text, int chars) {
        mapa = new HashMap();
        text = text.toLowerCase();

        if (chars < 2) {
            chars = 2;
        }

        for (int i = 0; i < text.length() - 1; i++) {
            if ((chars + i) > text.length()) {
                mapa.put(text, 1);
                break;
            }
            String subText = text.substring(i, (i + chars));
            int cant = serchEquals(text, subText, chars);
            mapa.put(subText, cant);
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

    @Override
    public HashMap deleteText(Long id) {
        System.out.println("ID:"   + id);
        Optional<Text> text = textRepo.findById(id);
        if (text.isPresent()) {
            Text textDelete = text.get();
            textDelete.setIsActive(Boolean.FALSE);
            textRepo.save(textDelete);
            return new HashMap();
        } else {
            return null;
        }
    }
}
