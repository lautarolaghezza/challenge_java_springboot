package com.challenge.java.spring.wolox.mapper;

import com.challenge.java.spring.wolox.entity.Album;

import java.util.HashMap;

public class AlbumMapper {

    public Album map(HashMap albumToMap) {
        Album album = new Album();
        album.setUserId((Integer) albumToMap.get("userId"));
        album.setId((Integer) albumToMap.get("id"));
        album.setTitle((String) albumToMap.get("title"));

        return album;

    }
}
