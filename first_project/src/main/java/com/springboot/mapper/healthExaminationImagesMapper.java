package com.springboot.mapper;

import com.springboot.model.healthExaminationImages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface healthExaminationImagesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(healthExaminationImages record);

    int insertSelective(healthExaminationImages record);

    healthExaminationImages selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(healthExaminationImages record);

    int updateByPrimaryKey(healthExaminationImages record);

    List<healthExaminationImages> findAllImages(Integer parentId);
}