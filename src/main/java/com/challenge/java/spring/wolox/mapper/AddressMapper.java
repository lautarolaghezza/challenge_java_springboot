package com.challenge.java.spring.wolox.mapper;

import com.challenge.java.spring.wolox.entity.Address;

import java.util.HashMap;

public class AddressMapper {
    public Address map(HashMap<String,?> addressToMap) {
        GeoMapper geoMapper = new GeoMapper();
        Address address = new Address();
        address.setStreet((String) addressToMap.get("street"));
        address.setSuite((String) addressToMap.get("suite"));
        address.setCity((String) addressToMap.get("city"));
        address.setZipcode((String) addressToMap.get("zipcode"));
        address.setGeo(geoMapper.map((HashMap<String, ?>) addressToMap.get("geo")));

        return address;
    }
}
