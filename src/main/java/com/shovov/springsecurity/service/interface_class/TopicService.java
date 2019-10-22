package com.shovov.springsecurity.service.interface_class;

import com.shovov.springsecurity.model.Topic;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    List<Topic> getAllTopicForALanguage(long languageId);
    Topic postTopicForALanguage(long languageId, Topic topic);
    Optional<Topic> getTopicById(@PathVariable long id);
    Optional<Topic> postTopicById(long id, Topic newTopic);
    void deleteTopicById(long id);

}
