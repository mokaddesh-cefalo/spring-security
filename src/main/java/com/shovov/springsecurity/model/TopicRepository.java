package com.shovov.springsecurity.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Long> {

    List<Topic> findTopicsByParentLanguage(Language language);
}
