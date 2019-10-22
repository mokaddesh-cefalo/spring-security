package com.shovov.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class TopicInformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(updatable = false)
    LocalDateTime createdDate;

    String topicInfoName;

    public TopicInformation(Topic parentTopic){
        this.parentTopic = parentTopic;
    }

    LocalDateTime lastModified;
    String topicInfoCodeUrl;
    String topicInfoDescription;

    @ManyToOne @JoinColumn(name = "topic_id")
    @JsonIgnore
    Topic parentTopic;

    @PrePersist
    void prePersist() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        lastModified = LocalDateTime.now();
    }
}