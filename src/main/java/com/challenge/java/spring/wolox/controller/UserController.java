package com.challenge.java.spring.wolox.controller;

import com.challenge.java.spring.wolox.entity.User;
import com.challenge.java.spring.wolox.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api")
public class UserController {

    RestTemplate restTemplate = new RestTemplate();
    final String uri = "https://jsonplaceholder.typicode.com/";
    UserMapper userMapper = new UserMapper();

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        HashMap result;
        try {
            result = restTemplate.getForObject(uri + "users/" + id, HashMap.class);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userMapper.map(result);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        HashMap[] result;
        List<User> users = new ArrayList<User>();
        try {
            result = restTemplate.getForObject(uri + "users", HashMap[].class);
            for (Object res : result) {
                users.add(userMapper.map((HashMap) res));
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
