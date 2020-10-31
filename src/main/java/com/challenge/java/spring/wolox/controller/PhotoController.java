package com.challenge.java.spring.wolox.controller;

import com.challenge.java.spring.wolox.entity.Album;
import com.challenge.java.spring.wolox.entity.Photo;
import com.challenge.java.spring.wolox.entity.User;
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
public class PhotoController extends BaseController{


    @GetMapping(value = "/photos/{id}")
    public ResponseEntity<Photo> getPhoto(@PathVariable Integer id) {
        return (ResponseEntity<Photo>) getForId(id, "photos");
    }


    @GetMapping(value = "/photos")
    public ResponseEntity<List<Photo>> getPhotos() {
        return (ResponseEntity<List<Photo>>) getAll("photos");
    }

    @GetMapping(value = "/photos/user/{id}")
    public ResponseEntity<List<Photo>> getAlbumsUser(@PathVariable Integer id) {
        List<Photo> photos = (List<Photo>) getAll("photos").getBody();
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
