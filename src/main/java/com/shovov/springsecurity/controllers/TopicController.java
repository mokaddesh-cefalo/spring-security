package com.shovov.springsecurity.controllers;

import com.shovov.springsecurity.model.Topic;
import com.shovov.springsecurity.service.interfaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/languages/{languageId}/topics")
public class TopicController {
    @Autowired TopicService topicService;

    String getLoggedInUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping
    @ResponseBody
    public List<Topic> getAllTopicForALanguage(@PathVariable(value = "languageId") long languageId){
        return topicService.getAllTopicForALanguage(languageId);
    }

    @PostMapping
    @ResponseBody
    public Topic postTopicForALanguage(@PathVariable(value = "languageId") long languageId, @RequestBody Topic topic){
        topic.setUserName(getLoggedInUserName());
        return topicService.postTopicForALanguage(languageId, topic);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTopicById(@PathVariable long id){
        Optional<Topic> topic = topicService.getTopicById(id);
        return topic.isPresent() ? new ResponseEntity(topic.get(), HttpStatus.FOUND) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}")
    public ResponseEntity postTopicById(@PathVariable long id, @RequestBody Topic topic){
        Optional<Topic> optionalTopic = topicService.postTopicById(id,topic);
        return optionalTopic.isPresent() ? new ResponseEntity(optionalTopic.get(), HttpStatus.FOUND) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopicById(@PathVariable long id){
        topicService.deleteTopicById(id);
        return new ResponseEntity("deleted", HttpStatus.OK);
    }
}
