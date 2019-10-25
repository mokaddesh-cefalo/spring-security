package com.shovov.springsecurity.service;

import com.shovov.springsecurity.model.Language;
import com.shovov.springsecurity.model.Topic;
import com.shovov.springsecurity.model.interfaces.TopicRepository;
import com.shovov.springsecurity.service.interfaces.TopicService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired TopicRepository topicRepository;
    @Autowired AuthorizationForDB authorizationForDB;

    @Override
    public List<Topic> getAllTopicForALanguage(long id){
        return topicRepository.findAllByParentLanguage(new Language(id));
    }

    @Override
    public Topic postTopicForALanguage(long languageId, Topic topic){
        if(authorizationForDB.checkParentLanguageIdForTopic(languageId)){

            topic.setParentLanguage(new Language(languageId));
            topic.setUserName(authorizationForDB.getLoggedInUserName());
            return topicRepository.save(topic);
        } else return null;
    }

    @Override
    public Optional<Topic> getTopicById(@PathVariable long id){
        return topicRepository.findById(id);
    }

    @Override
    public Optional<Topic> postTopicById(long id, Topic newTopic){
        Optional<Topic> topic = authorizationForDB
                .validateTopicChangeRequest(id);

        if(!topic.isPresent()) return topic;
        else {

            Topic prevTopic = topic.get();

            if(newTopic.getTopicName() != null) prevTopic.setTopicName(newTopic.getTopicName());
            return Optional.of(topicRepository.save(prevTopic));
        }
    }

    @Override
    public void deleteTopicById(long id){
        Optional<Topic> topic = authorizationForDB
                .validateTopicChangeRequest(id);
        if(topic.isPresent()) topicRepository.deleteById(id);
    }
}
