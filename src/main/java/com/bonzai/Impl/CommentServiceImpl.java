package com.bonzai.Impl;

import com.bonzai.model.Comment;
import com.bonzai.repository.CommentRepository;
import com.bonzai.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaurav on 7/6/17.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment save(Comment entity) {
        return commentRepository.save(entity);
    }

    @Override
    public Comment getById(Serializable id) {
        return commentRepository.findOne((Long)id);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        commentRepository.delete((Long)id);
    }
}
