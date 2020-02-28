package com.sakinramazan.microservices.blogservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity(name = "blog")
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String subject;

    @JsonIgnore
    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
    private List<Post> posts;

}
