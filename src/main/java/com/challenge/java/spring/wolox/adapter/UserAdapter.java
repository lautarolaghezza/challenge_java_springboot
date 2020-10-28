package com.challenge.java.spring.wolox.adapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedHashMap;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAdapter {
    private String id;
    private String name;
    private String username;
    private String email;
    private LinkedHashMap<String,LinkedHashMap<String,String>> address;
    private String phone;
    private String website;
    private LinkedHashMap<String,LinkedHashMap<String,String>> company;
}
