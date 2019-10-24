package com.shovov.springsecurity.service.interfaces;

import com.shovov.springsecurity.model.Language;

import java.util.List;
import java.util.Optional;

public interface LanguageService {
    Language postLanguage(Language language);
    List<Language> getLanguages();
    Optional<Language> getLanguageById(long id);
    Optional<Language> postLanguageById(long id,Language language);
    void deleteLanguageById(long id);
    String getLoggedInUserName();
}
