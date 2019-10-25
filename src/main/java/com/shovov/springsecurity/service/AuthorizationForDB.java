package com.shovov.springsecurity.service;

import com.shovov.springsecurity.model.Language;
import com.shovov.springsecurity.model.Topic;
import com.shovov.springsecurity.model.TopicInformation;
import com.shovov.springsecurity.model.interfaces.LanguageRepository;
import com.shovov.springsecurity.model.interfaces.TopicInformationRepository;
import com.shovov.springsecurity.model.interfaces.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationForDB {

    public String getLoggedInUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Autowired private LanguageRepository languageRepository;
    @Autowired private TopicRepository topicRepository;
    @Autowired private TopicInformationRepository topicInformationRepository;

    Optional<Language> validateLanguageChangeRequest(long id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return (optionalLanguage.isPresent() && optionalLanguage.get().getUserName().equals(getLoggedInUserName()) )
                ? optionalLanguage : Optional.ofNullable(null);
    }

    Optional<Topic> validateTopicChangeRequest(long id){
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        return (optionalTopic.isPresent() && optionalTopic.get().getUserName().equals(getLoggedInUserName()) )
                ? optionalTopic : Optional.ofNullable(null);
    }

    Optional<TopicInformation> validateTopicInformationChangeRequest(long id){
        Optional<TopicInformation> optionalTopicInformation = topicInformationRepository.findById(id);
        return (optionalTopicInformation.isPresent() && optionalTopicInformation.get().getUserName().equals(getLoggedInUserName()) )
                ? optionalTopicInformation : Optional.ofNullable(null);
    }

    Boolean checkParentLanguageIdForTopic(long languageId){
        Optional<Language> optionalLanguage = languageRepository.findById(languageId);
        return (optionalLanguage.isPresent() && optionalLanguage.get().getUserName().equals( getLoggedInUserName()));
    }

    Boolean checkParentTopicIdForTopicInformation(long topicId){
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        return (optionalTopic.isPresent() && optionalTopic.get().getUserName().equals( getLoggedInUserName()));
    }

}
