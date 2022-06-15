package com.springboot.service;

import com.springboot.model.healthExaminations;

import java.util.List;

public interface TestService {
    int insert(healthExaminations test);

    List<healthExaminations> findAllByparentId(Integer parentId);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(healthExaminations record);

    int findNewestId(Integer wwid);

    healthExaminations selectByPrimaryKey(Integer id);

    List<healthExaminations> findLike(String date,Integer parentId);
}
