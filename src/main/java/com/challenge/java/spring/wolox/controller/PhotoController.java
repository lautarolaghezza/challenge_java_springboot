package com.challenge.java.spring.wolox.controller;

import com.challenge.java.spring.wolox.entity.Album;
import com.challenge.java.spring.wolox.entity.Photo;
import com.challenge.java.spring.wolox.mapper.PhotoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api")
public class PhotoController {

    RestTemplate restTemplate = new RestTemplate();
    final String uri = "https://jsonplaceholder.typicode.com/";
    PhotoMapper photoMapper = new PhotoMapper();

    @GetMapping(value = "/photos/{id}")
    public ResponseEntity<Photo> getPhoto(@PathVariable Integer id) {
        HashMap result;
        try {
            result = restTemplate.getForObject(uri + "photos/" + id, HashMap.class);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Photo photo = photoMapper.map(result);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }


    @GetMapping(value = "/photos")
    public ResponseEntity<List<Photo>> getPhotos() {
        HashMap[] result;
        List<Photo> photos = new ArrayList<Photo>();
        try {
            result = restTemplate.getForObject(uri + "photos", HashMap[].class);
            for (Object res : result) {
                photos.add(photoMapper.map((HashMap) res));
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    @GetMapping(value = "/photos/user/{id}")
    public ResponseEntity<List<Photo>> getAlbumsUser(@PathVariable Integer id) {
        HashMap[] result;
        List<Photo> photos = new ArrayList<Photo>();
        try {
            result = restTemplate.getForObject(uri + "photos", HashMap[].class);
            for (Object res : result) {
                photos.add(photoMapper.map((HashMap) res));
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AlbumController albumController = new AlbumController();
        List<Integer> ids = albumsIds(Objects.requireNonNull(albumController.getAlbumsUser(id).getBody()));
        photos = photos.stream().filter(photo -> ids.contains(photo.getAlbumId())).collect(Collectors.toList());
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    public List<Integer> albumsIds(List<Album> albums) {
        List<Integer> ids = new ArrayList<>();
        for (Album album : albums) {
            ids.add(album.getId());
        }
        return ids;
    }

}
