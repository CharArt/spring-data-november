package com.charart.spring.dto;

import java.time.LocalDateTime;

public class PostDto {

    private Long post_id;

    private String title;

    private String content;

    private LocalDateTime create_at;

    private LocalDateTime update_at;

    public Long getPost_id() { return post_id; }
    public void setPost_id(Long post_id) { this.post_id = post_id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreate_at() { return create_at; }
    public void setCreate_at(LocalDateTime create_at) { this.create_at = create_at; }

    public LocalDateTime getUpdate_at() { return update_at; }
    public void setUpdate_at(LocalDateTime update_at) { this.update_at = update_at; }
}
