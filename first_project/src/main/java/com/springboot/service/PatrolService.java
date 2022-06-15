package com.springboot.service;

import com.springboot.model.patrols;

import java.util.List;

public interface PatrolService {
    int insert(patrols patrol);

    List<patrols> findAllByparentId(Integer parentId);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(patrols patrol);

    List<patrols> findLike(String date,Integer parentId);
}
