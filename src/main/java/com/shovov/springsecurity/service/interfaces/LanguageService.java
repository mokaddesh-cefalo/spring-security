package com.shovov.springsecurity.service.interface_class;

import com.shovov.springsecurity.model.Language;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface LanguageService {
    Language postLanguage(Language language);
    List<Language> getLanguages();
    Optional<Language> getLanguageById(long id);
    Optional<Language> postLanguageById(long id,Language language);
    void deleteLanguageById(long id);
}
