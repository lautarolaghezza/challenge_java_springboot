package com.challenge.java.spring.wolox.mapper;

import com.challenge.java.spring.wolox.entity.User;

import java.util.HashMap;

public class UserMapper {

    public UserMapper(){

    }
    public User map(HashMap<String, ?> userToMap){
        AddressMapper addressMapper = new AddressMapper();
        CompanyMapper companyMapper = new CompanyMapper();
        User user = new User();

        user.setId((Integer) userToMap.get("id"));
        user.setName((String) userToMap.get("name"));
        user.setUsername((String) userToMap.get("username"));
        user.setEmail((String) userToMap.get("email"));
        user.setAddress(addressMapper.map((HashMap<String, String>) userToMap.get("address")));
        user.setPhone((String) userToMap.get("phone"));
        user.setWebsite((String) userToMap.get("website"));
        user.setCompany(companyMapper.map((HashMap<String, String>) userToMap.get("company")));

        return user;
    }
}
