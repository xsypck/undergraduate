package com.springboot.mapper;

import com.springboot.model.fourProfiles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface fourProfilesMapper {
    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys=true, keyProperty="id")
    int insert(fourProfiles record);

    int insertSelective(fourProfiles record);

    fourProfiles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(fourProfiles record);

    int updateByPrimaryKey(fourProfiles record);

    List<fourProfiles> selectAll();

    List<fourProfiles> findModel(String model);

    Integer getFour(Integer wwid);

    List<fourProfiles> findAllByModel(@Param("parentId") Integer parentId,@Param("s") String s);
}