package com.shovov.springsecurity.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(nullable = false)
    String languageName;
    @Column(updatable = false)
    LocalDateTime createdDate;
    LocalDateTime lastModified;

    public Language(){
        topicList = new ArrayList<>();
    }

    public Language(String languageName){
        this.languageName = languageName;
    }

    @OneToMany
    List<Topic> topicList;

    @PrePersist
    void prePersist() {
        createdDate = LocalDateTime.now();
    }
}
