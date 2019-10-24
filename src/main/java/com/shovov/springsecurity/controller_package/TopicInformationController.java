package com.shovov.springsecurity.controller_package;

import com.shovov.springsecurity.model.TopicInformation;
import com.shovov.springsecurity.service.interfaces.TopicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/languages/{languageId}/topic/{topicId}/topicInformation")
public class TopicInformationController {

    @Autowired
    TopicInformationService topicInformationService;

    String getLoggedInUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping
    public List<TopicInformation> getTopicInformationByTopicId(@PathVariable long topicId){
        return topicInformationService.getTopicInformationByTopicId(topicId);
    }

    @PostMapping
    public TopicInformation postTopicInformationByTopicId(@PathVariable long topicId, @RequestBody TopicInformation topicInformation, HttpServletResponse response){
        topicInformation.setUserName(getLoggedInUserName());
        response.setStatus(201);
        return topicInformationService.postTopicInformationByTopicId(topicId, topicInformation);
    }

    @GetMapping("/{topicInformationId}")
    public ResponseEntity getTopicInformationById(@PathVariable long topicInformationId){
        Optional<TopicInformation> optionalTopicInformation = topicInformationService.getTopicInformationById(topicInformationId);

        if(!optionalTopicInformation.isPresent()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else return new ResponseEntity(optionalTopicInformation.get(), HttpStatus.OK);
    }

    @PostMapping("/{topicInformationId}")
    public ResponseEntity updateTopicInformationById(@PathVariable long topicInformationId, @RequestBody TopicInformation topicInformation){
        Optional<TopicInformation> optionalTopicInformation = topicInformationService.updateTopicInformationById(topicInformationId, topicInformation);

        if(!optionalTopicInformation.isPresent()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else return new ResponseEntity(optionalTopicInformation.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{topicInformationId}")
    public ResponseEntity deleteTopicInformationById(@PathVariable long topicInformationId){
        topicInformationService.deleteTopicInformationById(topicInformationId);
        return new ResponseEntity("Done", HttpStatus.OK);
    }
}
