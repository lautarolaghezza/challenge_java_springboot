package com.challenge.java.spring.wolox.controller;

import com.challenge.java.spring.wolox.entity.User;
import com.challenge.java.spring.wolox.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api")
public class UserController extends BaseController{



    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        return (ResponseEntity<User>) getForId(id, "users");
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return (ResponseEntity<List<User>>) getAll("users");
    }

}
