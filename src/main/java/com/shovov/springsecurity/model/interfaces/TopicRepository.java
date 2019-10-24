package com.shovov.springsecurity.model.interfaces;

import com.shovov.springsecurity.model.Language;
import com.shovov.springsecurity.model.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Long> {

    List<Topic> findTopicsByParentLanguage(Language language);
}
