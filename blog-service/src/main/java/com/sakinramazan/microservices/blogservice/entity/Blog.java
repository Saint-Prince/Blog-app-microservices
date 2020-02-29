package com.sakinramazan.microservices.blogservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@Entity(name = "blog")
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Builder.Default private String subject = "Sample static Blog Subject";

    @JsonIgnore
    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
    private List<Post> posts;

}
