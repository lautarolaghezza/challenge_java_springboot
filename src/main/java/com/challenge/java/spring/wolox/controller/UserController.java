package com.challenge.java.spring.wolox.controller;

import com.challenge.java.spring.wolox.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/api")
public class UserController extends BaseController {

    @PutMapping(value = "/users/{userId}/albums/{albumId}/{permission}")
    public ResponseEntity<User> setUserPermissionInAlbum(@PathVariable Integer userId, @PathVariable Integer albumId, @PathVariable String permission) {
        if (sharedAlbumService.changePermissionForUserInAlbum(userId, albumId, permission)) {
            return (ResponseEntity<User>) getForId(userId, "users");
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/users/albums/{albumId}/permission/{permission}")
    public ResponseEntity<List<User>> getUsersFromAlbumWithPermission(@PathVariable Integer albumId, @PathVariable String permission) {
        List<User> users = new ArrayList<>();
        sharedAlbumService.getUsersIdWithPermissionInAlbum(permission, albumId).forEach(id -> users.add((User) getForId(id, "users").getBody()));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        return (ResponseEntity<User>) getForId(id, "users");
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return (ResponseEntity<List<User>>) getAll("users");
    }


}
