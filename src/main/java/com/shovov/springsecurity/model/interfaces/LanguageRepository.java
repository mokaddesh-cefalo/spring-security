package com.shovov.springsecurity.model.interfaces;

import com.shovov.springsecurity.model.Language;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LanguageRepository extends CrudRepository<Language, Long> {
    List<Language> findLanguagesByUserName(String userName);
}
