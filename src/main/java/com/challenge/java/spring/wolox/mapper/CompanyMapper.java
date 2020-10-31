package com.challenge.java.spring.wolox.mapper;

import com.challenge.java.spring.wolox.entity.Company;

import java.util.HashMap;

public class CompanyMapper {

    public Company map(HashMap<String, String> companyToMap) {
        Company company = new Company();
        company.setName(companyToMap.get("name"));
        company.setCatchPhrase(companyToMap.get("catchPhrase"));
        company.setBs(companyToMap.get("bs"));

        return company;
    }
}
