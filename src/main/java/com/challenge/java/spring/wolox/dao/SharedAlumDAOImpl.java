package com.challenge.java.spring.wolox.dao;

import com.challenge.java.spring.wolox.mapper.IdRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SharedAlumDAOImpl implements SharedAlbumDAO {

    public SharedAlumDAOImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    NamedParameterJdbcTemplate template;


    @Override
    public boolean registerUserPermissionInAlbum(Integer userId, Integer albumId, String permission) {
        final String sql = "insert into users_permission(user_id , permission, album_id) values(:user_id, :permission,:album_id)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("permission", permission)
                .addValue("album_id", albumId);
        try {
            template.update(sql, param, holder);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean changePermissionForUserInAlbum(Integer userId, Integer albumId, String permission) {
        final String sql = "update users_permission set permission=:permission WHERE user_id=:user_id AND album_id=:album_id";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("permission", permission)
                .addValue("album_id", albumId);
        return 1 == template.update(sql, param, holder);
    }

    @Override
    public List<Integer> getUsersIdWithPermissionInAlbum(String permission, Integer albumId) {
        final String sql = "SELECT user_id FROM users_permission WHERE album_id=:album_id AND permission=:permission";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("permission", permission)
                .addValue("album_id", albumId);
        return template.query(sql, param, new IdRowMapper());
    }
}
