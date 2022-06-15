package com.springboot.service;

import com.springboot.model.fourProfiles;

import java.util.List;

public interface FourService {
    void save(fourProfiles fourprofile);

    List<fourProfiles> selectAll();

    void deleteByPrimaryKey(Integer id);

    List<fourProfiles> findModel(String model);

    int getFour(Integer wwid);

    List<fourProfiles> findAllByModel(Integer parentId, String s);
}
