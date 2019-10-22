package com.shovov.springsecurity.service.interface_class;

import com.shovov.springsecurity.model.TopicInformation;

import java.util.List;
import java.util.Optional;

public interface TopicInformationService {
    List<TopicInformation> getTopicInformationByTopicId(long topicId);
    TopicInformation postTopicInformationByTopicId(long topicId,TopicInformation topicInformation);
    Optional<TopicInformation> updateTopicInformationById(long topicInformationId, TopicInformation topicInformation);
    Optional<TopicInformation> getTopicInformationById(long topicInformationId);
    void deleteTopicInformationById(long topicInformationId);
}
