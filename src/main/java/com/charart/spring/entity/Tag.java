package com.charart.spring.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tag {

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tag_id;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;

    private String name;

    public Long getTag_id() { return tag_id; }
    public void setTag_id(Long tag_id) { this.tag_id = tag_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name;}
}
