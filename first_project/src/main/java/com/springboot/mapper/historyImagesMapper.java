package com.springboot.mapper;

import com.springboot.model.historyImages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface historyImagesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(historyImages record);

    int insertSelective(historyImages record);

    historyImages selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(historyImages record);

    int updateByPrimaryKey(historyImages record);

    List<historyImages> findAllByparentId(Integer parentId);
}