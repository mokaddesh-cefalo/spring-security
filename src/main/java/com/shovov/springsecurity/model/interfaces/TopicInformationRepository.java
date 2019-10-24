package com.shovov.springsecurity.model.interfaces;

import com.shovov.springsecurity.model.Topic;
import com.shovov.springsecurity.model.TopicInformation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicInformationRepository extends CrudRepository<TopicInformation, Long> {

    List<TopicInformation> findTopicInformationByParentTopic(Topic topic);
}
