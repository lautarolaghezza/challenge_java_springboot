package com.challenge.java.spring.wolox.mapper;

import com.challenge.java.spring.wolox.entity.Photo;

import java.util.HashMap;

public class PhotoMapper {

    public Photo map(HashMap photoToMap){
        Photo photo = new Photo();
        photo.setAlbumId((Integer) photoToMap.get("albumId"));
        photo.setId((Integer) photoToMap.get("id"));
        photo.setTitle((String) photoToMap.get("title"));
        photo.setUrl((String) photoToMap.get("url"));
        photo.setThumbnailUrl((String) photoToMap.get("thumbnailUrl"));

        return photo;

    }
}
