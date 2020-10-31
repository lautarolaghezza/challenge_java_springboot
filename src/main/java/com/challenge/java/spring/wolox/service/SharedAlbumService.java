package com.challenge.java.spring.wolox.service;

import java.util.List;

public interface SharedAlbumService {

    public boolean registerUserPermissionInAlbum(Integer userId, Integer albumId, String permission);

    public boolean changePermissionForUserInAlbum(Integer userId, Integer albumId, String permission);

    public List<Integer> getUsersIdWithPermissionInAlbum(String permission, Integer albumId);

}
