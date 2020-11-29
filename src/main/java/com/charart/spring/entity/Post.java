package com.charart.spring.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_content")
    private String content;

    @Column(name = "post_create_at")
    private LocalDateTime create_at;

    @Column(name = "post_update_at")
    private LocalDateTime update_at;

    @ManyToMany
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn( name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    public Long getPost_id() { return post_id; }
    public void setPost_id(Long post_id) { this.post_id = post_id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreate_at() { return create_at; }
    public void setCreate_at(LocalDateTime create_at) { this.create_at = create_at; }

    public LocalDateTime getUpdate_at() { return update_at; }
    public void setUpdate_at(LocalDateTime update_at) { this.update_at = update_at;}

    public List<Tag> getTags() { return tags; }
    public void setTags(List<Tag> tags) { this.tags = tags; }
}
