package com.charart.spring.dao.hibernate;

import com.charart.spring.dao.AbstractDAO;
import com.charart.spring.entity.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PostHibernateDAO implements AbstractDAO<Post> {

    private SessionFactory sessionFactory ;

    @Autowired
    public PostHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Post post) {
        sessionFactory.getCurrentSession().save(post);
    }

    @Override
    public List<Post> getAll(){
        return sessionFactory.getCurrentSession().createQuery(" SELECT p FROM Post p", Post.class).getResultList();
    }

    @Override
    public Post getById(long id) {
        return sessionFactory.getCurrentSession().get(Post.class, id);
    }

    @Override
    public void update(long id, Post post) {
        post.setPost_id(id);
        sessionFactory.getCurrentSession().merge(post);
    }

    @Override
    public void delete(long id) {
        Post post = sessionFactory.getCurrentSession().get(Post.class, id);
        sessionFactory.getCurrentSession().delete(post);
    }
}
