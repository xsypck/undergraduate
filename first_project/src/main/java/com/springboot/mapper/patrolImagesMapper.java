package com.springboot.mapper;

import com.springboot.model.patrolImages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface patrolImagesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(patrolImages record);

    int insertSelective(patrolImages record);

    patrolImages selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(patrolImages record);

    int updateByPrimaryKey(patrolImages record);

    List<patrolImages> findAllImages(Integer parentId);
}