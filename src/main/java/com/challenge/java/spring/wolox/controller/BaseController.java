package com.challenge.java.spring.wolox.controller;

import com.challenge.java.spring.wolox.entity.Album;
import com.challenge.java.spring.wolox.entity.Photo;
import com.challenge.java.spring.wolox.entity.User;
import com.challenge.java.spring.wolox.mapper.AlbumMapper;
import com.challenge.java.spring.wolox.mapper.PhotoMapper;
import com.challenge.java.spring.wolox.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api")
public abstract class BaseController {

    RestTemplate restTemplate = new RestTemplate();
    final String uri = "https://jsonplaceholder.typicode.com/";
    UserMapper userMapper = new UserMapper();
    PhotoMapper photoMapper = new PhotoMapper();
    AlbumMapper albumMapper = new AlbumMapper();

    protected ResponseEntity<?> getForId(Integer id, String type) {
        HashMap result;
        try {
            result = restTemplate.getForObject(uri + type + "/" + id, HashMap.class);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        switch (type) {
            case ("users"): {
                return new ResponseEntity<>(userMapper.map(result), HttpStatus.OK);
            }
            case ("photos"): {
                return new ResponseEntity<>(photoMapper.map(result), HttpStatus.OK);
            }
            case ("albums"): {
                return new ResponseEntity<>(albumMapper.map(result), HttpStatus.OK);
            }
            default: {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    protected ResponseEntity<?> getAll(String type) {
        HashMap[] result;
        try {
            result = restTemplate.getForObject(uri + type , HashMap[].class);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        switch (type) {
            case ("users"): {
                List<User> users = new ArrayList<User>();
                for (Object res : result) {
                    users.add(userMapper.map((HashMap) res));
                }
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
            case ("photos"): {
                List<Photo> photos = new ArrayList<Photo>();
                for (Object res : result) {
                    photos.add(photoMapper.map((HashMap) res));
                }
                return new ResponseEntity<>(photos, HttpStatus.OK);
            }
            case ("albums"): {
                List<Album> albums = new ArrayList<Album>();
                for (Object res : result) {
                    albums.add(albumMapper.map((HashMap) res));
                }
                return new ResponseEntity<>(albums, HttpStatus.OK);
            }
            default: {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    }
}
