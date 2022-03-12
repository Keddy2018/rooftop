package com.rooftop.api.controller;

import com.rooftop.api.dto.TextAddDto;
import com.rooftop.api.service.TextService;
import static com.rooftop.api.util.Constants.*;
import java.util.HashMap;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(REQ_MAPP_CLASS)
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
public class TextController {
    
    @Autowired
    TextService textService;

    @GetMapping() //ALL TEXT
    public ResponseEntity<?> getAllText(@Valid @RequestParam HashMap<String,String> map){
        return ResponseEntity.ok(textService.getTextPageable(map));
    }
    
    @GetMapping(REQ_MAPP_ID) //UNIT TEXT
    public ResponseEntity<?> getText(@Valid @PathVariable Long id){
        return ResponseEntity.ok(textService.getText(id));
    }
    
    @DeleteMapping(REQ_MAPP_ID) //DELETE TEXT
    public ResponseEntity<?> delete(@Valid @PathVariable Long id){
        return ResponseEntity.ok(textService.deleteText(id));
    }
    
    @PostMapping //ADD TEXT
    public ResponseEntity<?> addText(@Valid @RequestBody TextAddDto textAddDto){
        return ResponseEntity.ok(textService.addText(textAddDto));
    }
    
    
    
}
