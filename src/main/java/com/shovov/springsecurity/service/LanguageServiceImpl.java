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
    @Autowired AuthorizationForDB authorizationForDB;

    @Override
    public Language postLanguage(Language language){
        language.setUserName(authorizationForDB.getLoggedInUserName());
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

    @Override
    public void deleteLanguageById(long id){
        Optional<Language> optionalLanguage = authorizationForDB.validateLanguageChangeRequest(id);
        if(optionalLanguage.isPresent()) languageRepository.deleteById(id);
    }

    @Override
    public Optional<Language> postLanguageById(long id,Language language){
        Optional<Language> optionalLanguage = authorizationForDB.validateLanguageChangeRequest(id);

        if(!optionalLanguage.isPresent()) { return optionalLanguage; }

        Language prevLanguageDeltails = optionalLanguage.get();

        if(language.getLanguageName() != null) prevLanguageDeltails.setLanguageName(language.getLanguageName());
        return Optional.ofNullable(languageRepository.save(prevLanguageDeltails));
    }

    @Override
    public List<Language> findLanguagesByUserName(String userName){
        return languageRepository.findLanguagesByUserName(userName);
    }
}

