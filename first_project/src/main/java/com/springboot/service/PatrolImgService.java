package com.springboot.service;

import com.springboot.model.patrolImages;

import java.util.List;

public interface PatrolImgService {
    int deleteByPrimaryKey(Integer id);

    List<patrolImages> findAllImags(Integer parentId);

    void save(patrolImages patrolImage);
}
