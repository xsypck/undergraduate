package com.springboot.service;

import com.springboot.model.users;

import java.util.List;

public interface UserService {
    int deleteByPrimaryKey(String username);

    int insert(users record);

    int insertSelective(users record);

    users selectByPrimaryKey(String username);

    List<users> selectAll();

    int updateByPrimaryKeySelective(users record);

    int updateByPrimaryKey(users record);

    List<users> selectAllLike(String username);
}
