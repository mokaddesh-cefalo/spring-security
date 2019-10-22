package com.shovov.springsecurity.service;

import com.shovov.springsecurity.model.Language;
import com.shovov.springsecurity.model.Topic;
import com.shovov.springsecurity.model.TopicInformation;
import com.shovov.springsecurity.model.TopicInformationRepository;
import com.shovov.springsecurity.service.interface_class.TopicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicInformationServiceImpl implements TopicInformationService {

    @Autowired
    TopicInformationRepository topicInformationRepository;

    @Override
    public List<TopicInformation> getTopicInformationByTopicId(long topicId){
        return topicInformationRepository.findTopicInformationByParentTopic(new Topic(topicId));
    }

    @Override
    public TopicInformation postTopicInformationByTopicId(long topicId,TopicInformation topicInformation){
        topicInformation.setParentTopic( new Topic(topicId) );
        return topicInformationRepository.save(topicInformation);
    }

    @Override
    public Optional<TopicInformation> getTopicInformationById(long topicInformationId){
        return topicInformationRepository.findById(topicInformationId);
    }

    @Override
    public Optional<TopicInformation> updateTopicInformationById(long topicInformationId, TopicInformation topicInformation){
        Optional<TopicInformation> fetchTopicInformation = topicInformationRepository.findById(topicInformationId);
        if(!fetchTopicInformation.isPresent()) return fetchTopicInformation;

        TopicInformation prevTopicInformation = fetchTopicInformation.get();

        if(topicInformation.getTopicInfoName() != null)
            prevTopicInformation.setTopicInfoName( topicInformation.getTopicInfoName() );

        if(topicInformation.getTopicInfoCodeUrl() != null)
            prevTopicInformation.setTopicInfoCodeUrl( topicInformation.getTopicInfoCodeUrl() );

        if(topicInformation.getTopicInfoDescription() != null)
            prevTopicInformation.setTopicInfoDescription( topicInformation.getTopicInfoDescription() );

        return Optional.of(topicInformationRepository.save(prevTopicInformation));
    }

    @Override
    public void deleteTopicInformationById(long topicInformationId){
        topicInformationRepository.deleteById(topicInformationId);
    }
}
