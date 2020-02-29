package com.sakinramazan.microservices.blogservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(max = 150)
    private String subject;

    @JsonIgnore
    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
    private List<Post> posts;

}
