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
@EqualsAndHashCode
@Builder
@Entity(name = "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 50)
    private String writer;

    @NotNull
    @Size(max = 150)
    private String detail;

    @ManyToOne
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog;

    @JsonIgnore
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments;

}
