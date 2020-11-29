package com.charart.spring.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "comment_content")
    private String content;

    @Column(name = "comment_create_at")
    private LocalDateTime create_at;

    @Column(name = "comment_update_at")
    private LocalDateTime update_at;

    public Long getComment_id() { return comment_id; }
    public void setComment_id(Long comment_id) { this.comment_id = comment_id; }

    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreate_at() { return create_at; }
    public void setCreate_at(LocalDateTime create_at) { this.create_at = create_at; }

    public LocalDateTime getUpdate_at() { return update_at; }
    public void setUpdate_at(LocalDateTime update_at) { this.update_at = update_at; }
}
