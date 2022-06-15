package com.springboot.mapper;

import com.springboot.model.users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//通过全局Mapper已经注入spring容器了
@Mapper
public interface usersMapper {
    int deleteByPrimaryKey(String username);

    int insert(users record);

    int insertSelective(users record);

    users selectByPrimaryKey(String username);

    List<users> selectAll();

    int updateByPrimaryKeySelective(users record);

    int updateByPrimaryKey(users record);

    List<users> selectAllLike(String username);
}