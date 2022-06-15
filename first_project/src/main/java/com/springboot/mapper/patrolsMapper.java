package com.springboot.mapper;

import com.springboot.model.healthExaminations;
import com.springboot.model.patrols;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface patrolsMapper {
    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys=true, keyProperty="id")
    int insert(patrols record);

    int insertSelective(patrols record);

    patrols selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(patrols record);

    int updateByPrimaryKey(patrols record);

    List<patrols> findAllByparentId(Integer parentId);

    List<patrols> findLike(@Param("s") String s, @Param("parentId") Integer parentId);
}