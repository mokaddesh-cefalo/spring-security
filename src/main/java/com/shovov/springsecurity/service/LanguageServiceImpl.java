package com.shovov.springsecurity.service;

import com.shovov.springsecurity.model.Language;
import com.shovov.springsecurity.model.interfaces.LanguageRepository;
import com.shovov.springsecurity.service.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired LanguageRepository languageRepository;

    @Override
    public String getLoggedInUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public Language postLanguage(Language language){
        language.setUserName(getLoggedInUserName());
        return languageRepository.save(language);
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

    Optional<Language> validateLanguageChangeRequest(long id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return (optionalLanguage.isPresent() && optionalLanguage.get().getUserName().equals(getLoggedInUserName()) )
                ? optionalLanguage : Optional.ofNullable(null);
    }
    @Override
    public void deleteLanguageById(long id){
        Optional<Language> optionalLanguage = validateLanguageChangeRequest(id);
        if(optionalLanguage.isPresent()) languageRepository.deleteById(id);
    }

    @Override
    public Optional<Language> postLanguageById(long id,Language language){
        Optional<Language> optionalLanguage = validateLanguageChangeRequest(id);

        if(!optionalLanguage.isPresent()){
           return optionalLanguage;
        }

        Language prevLanguageDeltails = optionalLanguage.get();

        if(language.getLanguageName() != null) prevLanguageDeltails.setLanguageName(language.getLanguageName());
        return Optional.ofNullable(languageRepository.save(prevLanguageDeltails));
    }
}

