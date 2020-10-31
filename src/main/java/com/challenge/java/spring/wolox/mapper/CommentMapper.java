package com.challenge.java.spring.wolox.mapper;

import com.challenge.java.spring.wolox.entity.Comment;

import java.util.HashMap;


public class CommentMapper {

    public Comment map(HashMap commentToMap) {
        Comment comment = new Comment();
        comment.setPostId((Integer) commentToMap.get("postId"));
        comment.setId((Integer) commentToMap.get("id"));
        comment.setName((String) commentToMap.get("name"));
        comment.setEmail((String) commentToMap.get("email"));
        comment.setBody((String) commentToMap.get("body"));

        return comment;

    }
}
