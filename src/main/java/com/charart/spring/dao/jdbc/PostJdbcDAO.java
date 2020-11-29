package com.charart.spring.dao.jdbc;

import com.charart.spring.dto.PostDto;
import com.charart.spring.dao.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostJdbcDAO implements AbstractDAO<PostDto>{

    private  final RowMapper <PostDto> rowMapper = (resultSet, i) -> {
        PostDto postDto = new PostDto();
        postDto.setPost_id(resultSet.getLong(1));
        postDto.setTitle(resultSet.getString(2));
        postDto.setContent(resultSet.getString(3));
        postDto.setCreate_at(resultSet.getTimestamp(4).toLocalDateTime());
        if(resultSet.getTimestamp(5) != null)
            postDto.setUpdate_at(resultSet.getTimestamp(5).toLocalDateTime());
        return postDto;
    };

    private JdbcOperations jdbcOperations;

    @Autowired
    public PostJdbcDAO(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void create(PostDto dto) {
        final String QUERY = "INSERT INTO post (post_title, post_content, post_create_at, post_update_at) VALUES (?, ?, ?, ?);";
        jdbcOperations.update(QUERY, dto.getTitle(), dto.getContent(), dto.getCreate_at(), dto.getUpdate_at());
    }

    @Override
    public List getAll() {
      final String QUERY = "SELECT  post_id, post_title, post_content, post_create_at, post_update_at FROM post;";
        return jdbcOperations.query(QUERY, rowMapper);
    }

    @Override
    public PostDto getById(long id) {
        final String QUERY = "SELECT  post_id, post_title, post_content, post_create_at, post_update_at FROM post WHERE post_id = ?;";
        return jdbcOperations.queryForObject(QUERY, new Object[]{id}, rowMapper);
    }

    @Override
    public void update(long id, PostDto dto) {
        final String QUERY = "UPDATE post SET post_title = ?, post_content = ?, post_create_at = ?, post_update_at = ? WHERE post_id = ?;";
        jdbcOperations.update(QUERY, dto.getTitle(), dto.getContent(), dto.getCreate_at(), dto.getUpdate_at(), id);
    }

    @Override
    public void delete(long id) {
        final String QUERY = "DELETE FROM post WHERE post_id = ?;";
        jdbcOperations.update(QUERY, id);
    }
}
