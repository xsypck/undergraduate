package com.springboot.mapper;

import com.springboot.model.antiques;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface antiquesMapper {
    int deleteByPrimaryKey(Integer wwid);

    int insert(antiques record);

    int insertSelective(antiques record);

    antiques selectByPrimaryKey(Integer wwid);

    antiques selectByNum(String serialnum);

    List<antiques> selectAll();

    int updateByPrimaryKeySelective(antiques record);

    int updateByPrimaryKey(antiques record);

    List<antiques> selectAllLike(String s);

    List<antiques> findLike(@Param("wwname") String wwname, @Param("wwlocation1") String wwlocation1,@Param("wwlocation2") String wwlocation2,@Param("wwlocation3") String wwlocation3,@Param("wwyear") String wwyear,@Param("protectionlevel") String protectionlevel,@Param("wwcategory") String wwcategory);
}