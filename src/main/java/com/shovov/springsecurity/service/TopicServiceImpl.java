package com.shovov.springsecurity.service;

import com.shovov.springsecurity.controller_package.PublicUrlController;
import com.shovov.springsecurity.model.Language;
import com.shovov.springsecurity.model.Topic;
import com.shovov.springsecurity.model.TopicRepository;
import com.shovov.springsecurity.service.interface_class.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.management.OperatingSystemMXBean;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public List<Topic> getAllTopicForALanguage(long id){
        return topicRepository.findTopicsByParentLanguage(new Language(id));
    }

    public Topic postTopicForALanguage(long languageId, Topic topic){
        topic.setParentLanguage(new Language(languageId));
        return topicRepository.save(topic);
    }

    @Override
    public Optional<Topic> getTopicById(@PathVariable long id){
        return topicRepository.findById(id);
    }

    @Override
    public Optional<Topic> postTopicById(long id, Topic newTopic){
        Optional<Topic> topic = topicRepository.findById(id);

        if(!topic.isPresent()) return topic;
        else {

            Topic prevTopic = topic.get();

            if(newTopic.getTopicName() != null) prevTopic.setTopicName(newTopic.getTopicName());
            prevTopic.setLastModified(LocalDateTime.now());

            return Optional.of(topicRepository.save(prevTopic));
        }
    }

    @Override
    public void deleteTopicById(long id){
        topicRepository.deleteById(id);
    }
}
