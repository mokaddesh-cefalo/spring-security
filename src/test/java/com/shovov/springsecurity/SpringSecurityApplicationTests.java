package com.shovov.springsecurity;

import com.shovov.springsecurity.model.Language;
import com.shovov.springsecurity.model.interfaces.LanguageRepository;
import org.assertj.core.error.ShouldBeAfterYear;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Autowired
	LanguageRepository languageRepository;

	Optional<Language> validateLanguageChangeRequest(Long id){
		Optional<Language> optionalLanguage = languageRepository.findById(id);
		return (optionalLanguage.isPresent() && optionalLanguage.get().getUserName().equals("fish") )
				? optionalLanguage : Optional.ofNullable(null);
	}
	@Test
	void contextLoads() {
		Optional<Language> language = validateLanguageChangeRequest((long)5);
		if(language.isPresent()) System.out.println(language.get().getUserName());
	}

}
