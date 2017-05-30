package com.bonzai.repository;

import com.bonzai.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gaurav on 7/6/17.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByCampaignIdOrderByIdDesc(Long id);
}
