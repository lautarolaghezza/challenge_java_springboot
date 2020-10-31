package com.challenge.java.spring.wolox.service;

import com.challenge.java.spring.wolox.dao.SharedAlbumDAO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SharedAlbumServiceImpl implements SharedAlbumService {

    @Resource
    SharedAlbumDAO sharedAlbumDAO;

    @Override
    public boolean registerUserPermissionInAlbum(Integer userId, Integer albumId, String permission) {
        return sharedAlbumDAO.registerUserPermissionInAlbum(userId, albumId, permission);
    }

    @Override
    public boolean changePermissionForUserInAlbum(Integer userId, Integer albumId, String permission) {
        return sharedAlbumDAO.changePermissionForUserInAlbum(userId, albumId, permission);
    }

    @Override
    public List<Integer> getUsersIdWithPermissionInAlbum(String permission, Integer albumId) {
        return sharedAlbumDAO.getUsersIdWithPermissionInAlbum(permission, albumId);
    }
}
