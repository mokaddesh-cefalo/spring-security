package com.shovov.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(indexes = {@Index(columnList = "parent_topic_id")})
public class Topic implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String topicName;

    @ManyToOne @JoinColumn(name = "language_id", nullable = false)
    @JsonIgnore
    Language parentLanguage;

    @OneToMany(mappedBy = "parentTopic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<TopicInformation> topicInfoList;

    @Column(name = "parent_topic_id")
    long parentTopicId;

    LocalDateTime createdDate, lastModified;

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
