package com.shovov.springsecurity.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Topic implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(nullable = false)
    String topicName;
    @Column(updatable = false)
    LocalDateTime createdDate;
    LocalDateTime lastModified;

    @ManyToOne
    Language parentLanguage;
    @ManyToOne
    Topic parentTopic;
    @OneToMany
    List<TopicInformation> topicInfoList;

    public Topic(){
        topicInfoList = new ArrayList<>();
    }

    @PrePersist
    void prePersist() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        System.out.println("Here");
        lastModified = LocalDateTime.now();
    }
}
