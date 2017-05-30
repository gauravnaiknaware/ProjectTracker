package com.bonzai.controllers;

import com.bonzai.converters.CommentConverter;
import com.bonzai.dto.CommentDto;
import com.bonzai.model.Comment;
import com.bonzai.services.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gaurav on 7/6/17.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    final static Logger log = Logger.getLogger(CommentController.class);

    @Autowired
    CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Comment> saveComment(@RequestBody CommentDto commentDto) {
        Comment comment = CommentConverter.convertToComment(commentDto);
        commentService.save(comment);
        return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateComment(@RequestBody CommentDto commentDto) {
        Comment comment1 = commentService.getById(commentDto.getId());
        if (comment1 == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        commentService.save(CommentConverter.convertToComment(commentDto));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<CommentDto> getbyidComment(@PathVariable("id") Long id) {
        CommentDto commentDto = CommentConverter.convertToDto(commentService.getById(id));
        if (commentDto == null) {
            return new ResponseEntity<CommentDto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CommentDto>(commentDto,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CommentDto>> getAllComment() {
        List<CommentDto> commentDtos = CommentConverter.convertToListCommentDto(commentService.getAll());
        if (commentDtos.isEmpty()) {
            return new ResponseEntity<List<CommentDto>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<CommentDto>>(commentDtos,HttpStatus.OK);
    }

    @RequestMapping(value ="/{id}" ,method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteComment(@PathVariable("id")Long id) {
        Comment comment = commentService.getById(id);
        if (comment == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        commentService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }
}
