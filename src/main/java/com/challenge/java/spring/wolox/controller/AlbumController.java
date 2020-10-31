package com.challenge.java.spring.wolox.controller;

import com.challenge.java.spring.wolox.entity.Album;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api")
public class AlbumController extends BaseController {


    @PostMapping(value = "/albums/{albumId}/register/user/{userId}/{permission}")
    public ResponseEntity<?> registerUserWithPermissionInalbum(@PathVariable Integer albumId, @PathVariable Integer userId, @PathVariable String permission) {
        if (sharedAlbumService.registerUserPermissionInAlbum(userId, albumId, permission)) {
            return new ResponseEntity<>(getForId(albumId, "albums"), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/albums/{id}")
    public ResponseEntity<Album> getAlbum(@PathVariable Integer id) {
        return (ResponseEntity<Album>) getForId(id, "albums");
    }


    @GetMapping(value = "/albums")
    public ResponseEntity<List<Album>> getAlbums() {
        return (ResponseEntity<List<Album>>) getAll("albums");
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


