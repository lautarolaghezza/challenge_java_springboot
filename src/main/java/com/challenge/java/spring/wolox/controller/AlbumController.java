package com.challenge.java.spring.wolox.controller;

import com.challenge.java.spring.wolox.entity.Album;
import com.challenge.java.spring.wolox.entity.Photo;
import com.challenge.java.spring.wolox.mapper.AlbumMapper;
import com.challenge.java.spring.wolox.mapper.PhotoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api")
public class AlbumController {

    RestTemplate restTemplate = new RestTemplate();
    final String uri = "https://jsonplaceholder.typicode.com/";
    AlbumMapper albumMapper = new AlbumMapper();

    @GetMapping(value = "/albums/{id}")
    public ResponseEntity<Album> getAlbum(@PathVariable Integer id) {
        HashMap result;
        try {
            result = restTemplate.getForObject(uri + "albums/" + id, HashMap.class);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Album album = albumMapper.map(result);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }


    @GetMapping(value = "/albums")
    public ResponseEntity<List<Album>> getAlbums() {
        HashMap[] result;
        List<Album> albums = new ArrayList<Album>();
        try {
            result = restTemplate.getForObject(uri + "albums", HashMap[].class);
            for (Object res : result) {
                albums.add(albumMapper.map((HashMap) res));
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }


    @GetMapping(value = "/albums/user/{id}")
    public ResponseEntity<List<Album>> getAlbumsUser(@PathVariable Integer id) {
        HashMap[] result;
        List<Album> albums = new ArrayList<Album>();
        try {
            result = restTemplate.getForObject(uri + "albums", HashMap[].class);
            for (Object res : result) {
                albums.add(albumMapper.map((HashMap) res));
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        albums = albums.stream().filter(album -> album.getUserId().equals(id)).collect(Collectors.toList());
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

}


