package com.shovov.springsecurity.service;

import com.shovov.springsecurity.model.Topic;
import com.shovov.springsecurity.model.TopicInformation;
import com.shovov.springsecurity.model.interfaces.TopicInformationRepository;
import com.shovov.springsecurity.service.interfaces.TopicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicInformationServiceImpl implements TopicInformationService {

    @Autowired TopicInformationRepository topicInformationRepository;
    @Autowired AuthorizationForDB authorizationForDB;

    @Override
    public List<TopicInformation> getTopicInformationByTopicId(long topicId){
        return topicInformationRepository.findTopicInformationByParentTopic(new Topic(topicId));
    }

    @Override
    public TopicInformation postTopicInformationByTopicId(long topicId,TopicInformation topicInformation){
        if(authorizationForDB.checkParentTopicIdForTopicInformation(topicId)){
            topicInformation.setParentTopic( new Topic(topicId) );
            topicInformation.setUserName(authorizationForDB.getLoggedInUserName());
            return topicInformationRepository.save(topicInformation);
        } else return null;
    }

    @Override
    public Optional<TopicInformation> getTopicInformationById(long topicInformationId){
        return topicInformationRepository.findById(topicInformationId);
    }

    @Override
    public Optional<TopicInformation> updateTopicInformationById(long topicInformationId, TopicInformation topicInformation){
        Optional<TopicInformation> fetchTopicInformation = authorizationForDB.validateTopicInformationChangeRequest(topicInformationId);
        if(!fetchTopicInformation.isPresent()) return fetchTopicInformation;

        TopicInformation prevTopicInformation = updateOldTopicInformation(topicInformation, fetchTopicInformation);

        return Optional.of(topicInformationRepository.save(prevTopicInformation));
    }

    private TopicInformation updateOldTopicInformation(TopicInformation topicInformation, Optional<TopicInformation> fetchTopicInformation) {
        TopicInformation prevTopicInformation = fetchTopicInformation.get();

        if(topicInformation.getTopicInfoName() != null)
            prevTopicInformation.setTopicInfoName( topicInformation.getTopicInfoName() );

        if(topicInformation.getTopicInfoCodeUrl() != null)
            prevTopicInformation.setTopicInfoCodeUrl( topicInformation.getTopicInfoCodeUrl() );

        if(topicInformation.getTopicInfoDescription() != null)
            prevTopicInformation.setTopicInfoDescription( topicInformation.getTopicInfoDescription() );
        return prevTopicInformation;
    }

    @Override
    public void deleteTopicInformationById(long topicInformationId){
        Optional<TopicInformation> fetchTopicInformation = authorizationForDB.validateTopicInformationChangeRequest(topicInformationId);
        if(fetchTopicInformation.isPresent()) topicInformationRepository.deleteById(topicInformationId);
    }
}
