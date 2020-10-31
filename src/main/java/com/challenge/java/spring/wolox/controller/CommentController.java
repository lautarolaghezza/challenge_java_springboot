package com.challenge.java.spring.wolox.controller;

import com.challenge.java.spring.wolox.entity.Comment;
import com.challenge.java.spring.wolox.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api")
public class CommentController extends BaseController {

    @RequestMapping(value = "/comments/name/{name}")
    public ResponseEntity<List<Comment>> getComentByName(@PathVariable String name) {
        List<Comment> comments = (List<Comment>) getAll("comments").getBody();
        if (comments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comments = comments.stream().filter(comment -> comment.getName().equals(name)).collect(Collectors.toList());
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }

    @RequestMapping(value = "/comments/user/{id}")
    public ResponseEntity<List<Comment>> getcommentByUserId(@PathVariable Integer id) {
        List<Comment> comments = (List<Comment>) getAll("comments").getBody();
        User user = (User) getForId(id, "users").getBody();
        if (user == null || comments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comments = comments.stream().filter(comment -> comment.getEmail().equals(user.getEmail())).collect(Collectors.toList());
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }
}
