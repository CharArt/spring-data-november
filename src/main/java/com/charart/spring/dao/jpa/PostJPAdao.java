package com.charart.spring.dao.jpa;

import com.charart.spring.dao.AbstractDAO;
import com.charart.spring.entity.Post;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostJPAdao implements AbstractDAO<Post> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Post post) { em.persist(post); }

    @Override
    public List<Post> getAll(){
        return em.createQuery(" SELECT p FROM Post p", Post.class).getResultList();
    }

    @Override
    public Post getById(long id) { return em.find(Post.class, id); }

    @Override
    public void update(long id, Post post) {
        post.setPost_id(id);
        em.merge(post);
    }

    @Override
    public void delete(long id) {
        Post post = em.find(Post.class, id);
        em.remove(post);
    }
}