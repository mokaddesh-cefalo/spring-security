package com.shovov.springsecurity.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicInformationRepository extends CrudRepository<TopicInformation, Long> {

    List<TopicInformation> findTopicInformationByParentTopic(Topic topic);
}
