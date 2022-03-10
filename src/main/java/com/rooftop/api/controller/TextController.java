package com.rooftop.api.controller;

import com.rooftop.api.dto.TextAddDto;
import com.rooftop.api.service.TextService;
import static com.rooftop.api.util.Constants.REQ_MAPP_CLASS;
import static com.rooftop.api.util.Constants.REQ_MAPP_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(REQ_MAPP_CLASS)
public class TextController {
    
    @Autowired
    TextService textService;
    
    @GetMapping //ALL
    public ResponseEntity<?> getAllText(){
        return null;
    }
    
    @GetMapping("/por") //UNIT
    public ResponseEntity<?> getText(){
        return null;
    }
    
    @DeleteMapping(REQ_MAPP_ID) //Delete
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(textService.deleteText(id));
    }
    
    @PostMapping //Add
    public ResponseEntity<?> addText(@RequestBody TextAddDto textAddDto){
        return ResponseEntity.ok(textService.addText(textAddDto));
    }
    
    
    
}
