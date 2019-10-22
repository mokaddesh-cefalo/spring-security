package com.shovov.springsecurity.service;

import com.shovov.springsecurity.model.Language;
import com.shovov.springsecurity.model.LanguageRepository;
import com.shovov.springsecurity.service.interface_class.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired LanguageRepository languageRepository;

    private Language saveLanguage(Language language){
        if(language.getId() != null) language.setLastModified(LocalDateTime.now());
        return languageRepository.save(language);
    }

    @Override
    public Language postLanguage(Language language){
        return saveLanguage(language);
    }

    @Override
    public List<Language> getLanguages(){
        List<Language> languages = new ArrayList<>();
        languageRepository.findAll().forEach(language -> languages.add(language));
        return languages;
    }

    @Override
    public Optional<Language> getLanguageById(long id){
        return languageRepository.findById(id);
    }

    @Override
    public void deleteLanguageById(long id){
         languageRepository.deleteById(id);
    }

    @Override
    public Optional<Language> postLanguageById(long id,Language language){

        Optional<Language> optionalLanguage = languageRepository.findById(id);

        if(!optionalLanguage.isPresent()){
            System.out.println("FUca");
           return optionalLanguage;
        }

        Language prevLanguageDeltails = optionalLanguage.get();

        if(language.getLanguageName() != null) prevLanguageDeltails.setLanguageName(language.getLanguageName());
        return Optional.ofNullable(saveLanguage(prevLanguageDeltails));
    }
}

