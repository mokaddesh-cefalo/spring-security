package com.shovov.springsecurity.controllers;

import com.shovov.springsecurity.model.Language;
import com.shovov.springsecurity.service.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/languages")
public class LanguageController {


    @Autowired LanguageService languageService;

    @GetMapping
    public List<Language> getLanguages(){
        return languageService.getLanguages();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Language postLanguage(@RequestBody Language language){
        return languageService.postLanguage(language);
    }

    @GetMapping("/{id}")
    public ResponseEntity getLanguageById(@PathVariable long id){
       Optional<Language> optionalLanguage = languageService.getLanguageById(id);

        if(!optionalLanguage.isPresent()){
               return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(optionalLanguage.get(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/{id}")
    @ResponseBody
    public Language postLanguageById(@PathVariable long id, @RequestBody Language language, HttpServletResponse response){

        Optional<Language> optionalLanguage =  languageService.postLanguageById(id, language);
        if(!optionalLanguage.isPresent()) {
            try {
                response.sendError(404);
                return null;
            }catch (IOException e){
                e.fillInStackTrace();
            }
        }


        return optionalLanguage.get();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteLanguageById(@PathVariable long id){
        languageService.deleteLanguageById(id);
        return new  ResponseEntity<>(HttpStatus.OK);
    }

}
