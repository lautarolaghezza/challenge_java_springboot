package com.challenge.java.spring.wolox.controller;

import com.challenge.java.spring.wolox.entity.User;
import com.challenge.java.spring.wolox.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api")
public class UserController {

    final String uri = "https://jsonplaceholder.typicode.com/";


    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){

        RestTemplate restTemplate = new RestTemplate();
        HashMap result;
        try {
            result = restTemplate.getForObject(uri+"users/"+ id, HashMap.class);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserMapper userMapper = new UserMapper();
        User user = userMapper.map(result);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
