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
@Table(indexes = {@Index(columnList = "language_id"), @Index(columnList = "user_name")})
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

    @Column(name = "user_name", updatable = false)
    String userName;


    LocalDateTime createdDate, lastModified;

    public Topic(){
        topicInfoList = new ArrayList<>();
    }
    public Topic(Long id){ this.id = id; }

    @PrePersist
    void prePersist() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        lastModified = LocalDateTime.now();
    }
}
