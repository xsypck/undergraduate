package com.springboot.mapper;

import com.springboot.model.healthExaminations;
import com.springboot.model.patrols;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface healthExaminationsMapper {
    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys=true, keyProperty="id")
    int insert(healthExaminations record);

    int insertSelective(healthExaminations record);

    healthExaminations selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(healthExaminations record);

    int updateByPrimaryKey(healthExaminations record);

    List<healthExaminations> findAllByparentId(Integer parentId);

    Integer findNewestId(Integer wwid);

    List<healthExaminations> findLike(@Param("s") String s, @Param("parentId") Integer parentId);
}