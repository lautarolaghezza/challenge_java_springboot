package com.challenge.java.spring.wolox.mapper;

import com.challenge.java.spring.wolox.entity.Geo;

import java.util.HashMap;

public class GeoMapper {

    public Geo map(HashMap<String, ?> geoToMap) {
        Geo geo = new Geo();
        geo.setLat((String) geoToMap.get("lat"));
        geo.setLng((String) geoToMap.get("lng"));
        return geo;
    }
}
