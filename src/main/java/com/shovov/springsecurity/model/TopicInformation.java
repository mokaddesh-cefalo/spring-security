package com.shovov.springsecurity.model;

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

    @Column(nullable = false)
    String topicInfoName;

    LocalDateTime lastModified;
    String topicInfoCodeUrl;
    String topicInfoDescription;

    @ManyToOne @JoinColumn(name = "topic_id", nullable = false)
    Topic parentTopic;

    @PrePersist
    void prePersist() {
        System.out.println("FUCK");
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        System.out.println("Here");
        lastModified = LocalDateTime.now();
    }
}